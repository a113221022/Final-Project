import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private String customerStatus;

    public Order(String status) {
        this.customerStatus = status;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateFinalTotal() {
        double subtotal = items.stream().mapToDouble(MenuItem::calculatePrice).sum();
        double discounted = subtotal;

        if (subtotal >= 1000) discounted -= 100;
        if ("Member".equals(customerStatus)) discounted *= 0.9;
        
        return Math.max(0, discounted);
    }
}