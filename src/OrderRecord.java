public class OrderRecord {
    private final String orderId;
    private final ShoppingCart cart;
    private final Customer customer;
    private final String transactionId;
    private final String trackingNumber;
    private boolean cancelled;

    public OrderRecord(String orderId, ShoppingCart cart, Customer customer, String transactionId, String trackingNumber) {
        this.orderId = orderId;
        this.cart = cart;
        this.customer = customer;
        this.transactionId = transactionId;
        this.trackingNumber = trackingNumber;
        this.cancelled = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void markCancelled() {
        this.cancelled = true;
    }
}