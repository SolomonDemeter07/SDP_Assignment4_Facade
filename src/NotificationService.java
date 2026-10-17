public class NotificationService {
    public void sendOrderConfirmation(String email, String orderId, String trackingNumber) {
        System.out.println("[NotificationService] Email to " + email + ": Order " + orderId + " confirmed! Tracking: " + trackingNumber);
    }

    public void sendOrderCancellation(String email, String orderId) {
        System.out.println("[NotificationService] Email to " + email + ": Order " + orderId + " has been cancelled and refunded.");
    }

    public void sendPaymentFailureAlert(String email, String itemId) {
        System.out.println("[NotificationService] Email to " + email + ": Order failed due to declined payment for item " + itemId + ".");
    }
}