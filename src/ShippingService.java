import java.util.UUID;

public class ShippingService {
    public String createShipment(String itemId, int quantity, String address) {
        String trackingNumber = "SHIP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        System.out.println("[ShippingService] Scheduled delivery of " + quantity + "x " + itemId + " to '" + address + "'. Tracking: " + trackingNumber);
        return trackingNumber;
    }

    public void cancelShipment(String trackingNumber) {
        System.out.println("[ShippingService] Shipment " + trackingNumber + " has been cancelled.");
    }
}