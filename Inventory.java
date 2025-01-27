import java.util.ArrayList;
import java.util.List;

interface SupplierOperations {
    void addSupplier(int id, String name, String contactInfo);

    void listSuppliers();
}

class Product {
    private int id;
    private String name;
    private int stock;
    private double price;

    public Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLowStock() {
        return stock < 10; // Low-stock threshold is set to 10.
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Stock: " + stock + ", Price: $" + price;
    }
}

class Supplier {
    private int id;
    private String name;
    private String contactInfo;

    public Supplier(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Supplier ID: " + id + ", Name: " + name + ", Contact Info: " + contactInfo;
    }
}

class Inventory implements SupplierOperations {
    private List<Product> products = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();

    // Product Operations
    public void addProduct(int id, String name, int stock, double price) {
        products.add(new Product(id, name, stock, price));
        System.out.println("Product added: " + name);
    }

    public void updateProductStock(int id, int quantity) {
        Product product = findProductById(id);
        if (product != null) {
            product.updateStock(quantity);
            System.out.println("Stock updated for product: " + product.getName());
        } else {
            System.out.println("Product not found with ID: " + id);
        }
    }

    public void removeProduct(int id) {
        Product product = findProductById(id);
        if (product != null) {
            products.remove(product);
            System.out.println("Product removed: " + product.getName());
        } else {
            System.out.println("Product not found with ID: " + id);
        }
    }

    public void listProducts() {
        System.out.println("Current Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void checkLowStock() {
        System.out.println("Low Stock Products:");
        for (Product product : products) {
            if (product.isLowStock()) {
                System.out.println(product);
            }
        }
    }

    // Supplier Operations
    @Override
    public void addSupplier(int id, String name, String contactInfo) {
        suppliers.add(new Supplier(id, name, contactInfo));
        System.out.println("Supplier added: " + name);
    }

    @Override
    public void listSuppliers() {
        System.out.println("Suppliers:");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
    }

    // Helper Methods
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding Suppliers
        inventory.addSupplier(1, "Global Supply Co.", "contact@globalsupply.com");
        inventory.addSupplier(2, "Fast Traders Inc.", "support@fasttraders.com");

        // Adding Products
        inventory.addProduct(101, "Laptop", 15, 1000.0);
        inventory.addProduct(102, "Smartphone", 5, 800.0);
        inventory.addProduct(103, "Headphones", 50, 50.0);

        // Listing all products and suppliers
        inventory.listProducts();
        inventory.listSuppliers();

        // Updating product stock
        inventory.updateProductStock(102, 10);
        inventory.updateProductStock(104, 5); // Non-existent product

        // Checking low-stock products
        inventory.checkLowStock();

        // Removing a product
        inventory.removeProduct(103);
        inventory.removeProduct(105); // Non-existent product

        // Listing all products again
        inventory.listProducts();
    }
}
