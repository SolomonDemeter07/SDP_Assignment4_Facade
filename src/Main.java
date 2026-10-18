public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();

        Customer alice = new Customer("C-101", "Alice", "alice@example.com", "Astana, Mangilik El 55", 2500.0);
        ShoppingCart laptopCart = new ShoppingCart("LAPTOP-PRO", 1, 1200.0);

        String aliceOrderId = orderFacade.placeOrder(laptopCart, alice);

        Customer bob = new Customer("C-102", "Bob", "bob@example.com", "Almaty, Abay Ave 10", 100.0);
        ShoppingCart expensiveCart = new ShoppingCart("LAPTOP-PRO", 2, 1200.0);

        try {
            orderFacade.placeOrder(expensiveCart, bob);
        } catch (OrderProcessingException e) {
            System.out.println("[Client] Handled expected error: " + e.getMessage());
        }

        orderFacade.cancelOrder(aliceOrderId);
    }
}