import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Stock: " + stock + ")";
    }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}

class Order {
    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private double totalCost;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            products.add(product);
            product.updateStock(-quantity);
            totalCost += product.getPrice() * quantity;
        } else {
            System.out.println("Not enough stock for product: " + product.getName());
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer.getName()).append(":\n");
        for (Product product : products) {
            sb.append("  - ").append(product).append("\n");
        }
        sb.append("Total Cost: $").append(totalCost).append("\n");
        return sb.toString();
    }
}

public class ECommerce {
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addProduct(String name, double price, int stock) {
        products.add(new Product(name, price, stock));
    }

    public void removeProduct(String name) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
    }

    public void listProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void createOrder(Customer customer, List<String> productNames, List<Integer> quantities) {
        if (productNames.size() != quantities.size()) {
            System.out.println("Product names and quantities do not match.");
            return;
        }

        Order order = new Order(customer);
        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            int quantity = quantities.get(i);
            Product product = findProductByName(productName);
            if (product != null) {
                order.addProduct(product, quantity);
            } else {
                System.out.println("Product not found: " + productName);
            }
        }
        orders.add(order);
        System.out.println("Order Created:\n" + order);
        saveOrderToFile(order);
    }

    private Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private void saveOrderToFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            writer.write(order.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ECommerce ecommerce = new ECommerce();
        ecommerce.addProduct("Laptop", 1000.0, 5);
        ecommerce.addProduct("Smartphone", 500.0, 10);
        ecommerce.listProducts();

        Customer customer = new Customer("John Doe", "johndoe@example.com");
        List<String> productNames = List.of("Laptop", "Smartphone");
        List<Integer> quantities = List.of(1, 2);

        ecommerce.createOrder(customer, productNames, quantities);
    }
}