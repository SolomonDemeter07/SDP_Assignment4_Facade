import java.util.UUID;

public class PaymentProcessor {
    public String chargeCustomer(Customer customer, double amount) {
        if (customer.getBalance() < amount) {
            System.out.println("[PaymentProcessor] Payment declined for " + customer.getName() + ". Required: $" + amount + ", Available: $" + customer.getBalance());
            return null;
        }
        customer.deductBalance(amount);
        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        System.out.println("[PaymentProcessor] Charged $" + amount + " to " + customer.getName() + ". Transaction ID: " + transactionId);
        return transactionId;
    }

    public void refundTransaction(Customer customer, double amount, String transactionId) {
        customer.refundBalance(amount);
        System.out.println("[PaymentProcessor] Refunded $" + amount + " for transaction " + transactionId + " to " + customer.getName());
    }
}