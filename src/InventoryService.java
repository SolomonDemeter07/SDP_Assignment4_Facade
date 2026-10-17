import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("LAPTOP-PRO", 5);
        stock.put("MECH-KEYBOARD", 10);
        stock.put("GAMING-MOUSE", 2);
    }

    public boolean checkAndReserveStock(String itemId, int quantity) {
        int available = stock.getOrDefault(itemId, 0);
        if (available >= quantity) {
            stock.put(itemId, available - quantity);
            System.out.println("[InventoryService] Reserved " + quantity + "x " + itemId + ". Remaining stock: " + stock.get(itemId));
            return true;
        }
        System.out.println("[InventoryService] Insufficient stock for " + itemId + " (Requested: " + quantity + ", Available: " + available + ")");
        return false;
    }

    public void releaseStock(String itemId, int quantity) {
        int current = stock.getOrDefault(itemId, 0);
        stock.put(itemId, current + quantity);
        System.out.println("[InventoryService] Released " + quantity + "x " + itemId + " back to inventory. Current stock: " + stock.get(itemId));
    }

    public int getAvailableStock(String itemId) {
        return stock.getOrDefault(itemId, 0);
    }
}