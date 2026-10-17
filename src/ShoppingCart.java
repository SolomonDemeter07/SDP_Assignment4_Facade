public class ShoppingCart {
    private final String itemId;
    private final int quantity;
    private final double unitPrice;

    public ShoppingCart(String itemId, int quantity, double unitPrice) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return quantity * unitPrice;
    }
}