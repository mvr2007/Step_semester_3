import java.util.*;

// --- Payment Strategy Interface ---
interface PaymentMethod {
    boolean processPayment(double amount);
    String getMethodName();
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return true; // Simulating success
    }

    @Override
    public String getMethodName() { return "Credit Card"; }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return false; // Simulating failure
    }

    @Override
    public String getMethodName() { return "PayPal"; }
}

// --- Domain Models ---
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getName() { return name; }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() { return product.getPrice() * quantity; }
}

enum OrderStatus { PENDING, PAID, FAILED }

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public double calculateTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public OrderStatus getStatus() { return status; }

    public void processPayment(PaymentMethod paymentMethod) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + paymentMethod.getMethodName() + " for Order " + customer.getName() + ".");

        boolean success = paymentMethod.processPayment(calculateTotalAmount());
        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for Order " + customer.getName() + " successful. Order status: " + status);
        } else {
            this.status = OrderStatus.PENDING;
            System.out.println("Payment for Order " + customer.getName() + " failed. Order status: " + status);
        }
    }
}

// --- Main Execution ---
public class Main {
    public static void main(String[] args) {
        Product prodA = new Product("Product A", 50.0);
        Product prodB = new Product("Product B", 30.0);
        Product prodC = new Product("Product C", 100.0);

        Customer custX = new Customer("X");
        Customer custY = new Customer("Y");
        Customer custZ = new Customer("Z");

        // 1. Customer X creates order and pays with Credit Card
        Order orderX = new Order("O1", custX);
        orderX.addItem(prodA, 2);
        orderX.addItem(prodB, 1);
        orderX.processPayment(new CreditCardPayment());

        // 2. Customer Y creates an empty order and attempts payment
        Order orderY = new Order("O2", custY);
        orderY.processPayment(new CreditCardPayment());

        // 3. Customer Z creates order and payment fails via PayPal
        Order orderZ = new Order("O3", custZ);
        orderZ.addItem(prodC, 1);
        orderZ.processPayment(new PayPalPayment());
    }
}
