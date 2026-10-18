import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentProcessor paymentProcessor;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    private final Map<String, OrderRecord> activeOrders;

    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentProcessor = new PaymentProcessor();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
        this.activeOrders = new HashMap<>();
    }

    public String placeOrder(ShoppingCart cart, Customer customer) {
        System.out.println("\n--- Starting Order Placement for " + customer.getName() + " ---");

        boolean reserved = inventoryService.checkAndReserveStock(cart.getItemId(), cart.getQuantity());
        if (!reserved) {
            throw new OrderProcessingException("Cannot place order: Insufficient stock for item " + cart.getItemId());
        }

        String transactionId = paymentProcessor.chargeCustomer(customer, cart.getTotalAmount());
        if (transactionId == null) {
            System.out.println("[OrderFacade] Payment failed! Initiating rollback of reserved inventory...");
            inventoryService.releaseStock(cart.getItemId(), cart.getQuantity());
            notificationService.sendPaymentFailureAlert(customer.getEmail(), cart.getItemId());
            throw new OrderProcessingException("Order aborted: Payment declined for customer " + customer.getName());
        }

        String trackingNumber = shippingService.createShipment(cart.getItemId(), cart.getQuantity(), customer.getAddress());
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        OrderRecord record = new OrderRecord(orderId, cart, customer, transactionId, trackingNumber);
        activeOrders.put(orderId, record);

        notificationService.sendOrderConfirmation(customer.getEmail(), orderId, trackingNumber);
        System.out.println("--- Order " + orderId + " Successfully Placed ---");
        return orderId;
    }

    public void cancelOrder(String orderId) {
        System.out.println("\n--- Starting Cancellation for Order " + orderId + " ---");
        OrderRecord record = activeOrders.get(orderId);

        if (record == null || record.isCancelled()) {
            throw new OrderProcessingException("Order " + orderId + " not found or already cancelled.");
        }

        shippingService.cancelShipment(record.getTrackingNumber());
        paymentProcessor.refundTransaction(record.getCustomer(), record.getCart().getTotalAmount(), record.getTransactionId());
        inventoryService.releaseStock(record.getCart().getItemId(), record.getCart().getQuantity());
        record.markCancelled();

        notificationService.sendOrderCancellation(record.getCustomer().getEmail(), orderId);
        System.out.println("--- Order " + orderId + " Successfully Cancelled ---");
    }
}