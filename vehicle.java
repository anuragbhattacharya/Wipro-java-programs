import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    private String id;
    private String brand;
    private double rentalRate;
    private boolean isAvailable;

    public Vehicle(String id, String brand, double rentalRate) {
        this.id = id;
        this.brand = brand;
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    public abstract String getVehicleType();

    @Override
    public String toString() {
        return getVehicleType() + " - ID: " + id + ", Brand: " + brand + ", Rental Rate: $" + rentalRate +
                "/day, Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Car extends Vehicle {
    private int seats;

    public Car(String id, String brand, double rentalRate, int seats) {
        super(id, brand, rentalRate);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    private int engineCC;

    public Bike(String id, String brand, double rentalRate, int engineCC) {
        super(id, brand, rentalRate);
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}

class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Phone: " + phoneNumber;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int rentalDays;

    public Rental(Vehicle vehicle, Customer customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public double calculateRentalCost() {
        return vehicle.getRentalRate() * rentalDays;
    }

    @Override
    public String toString() {
        return "Rental: " + vehicle.getVehicleType() + " [" + vehicle.getBrand() + "] rented by " + customer.getName() +
                ", Days: " + rentalDays + ", Total Cost: $" + calculateRentalCost();
    }
}

class VehicleRentalSystem {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println(vehicle.getVehicleType() + " added: " + vehicle.getBrand());
    }

    public void listVehicles() {
        System.out.println("Available Vehicles:");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                System.out.println(vehicle);
            }
        }
    }

    public void rentVehicle(String vehicleId, Customer customer, int rentalDays) {
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle != null && vehicle.isAvailable()) {
            vehicle.setAvailability(false);
            Rental rental = new Rental(vehicle, customer, rentalDays);
            rentals.add(rental);
            System.out.println("Vehicle rented successfully:\n" + rental);
        } else {
            System.out.println("Vehicle with ID " + vehicleId + " is not available for rent.");
        }
    }

    public void returnVehicle(String vehicleId) {
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle != null && !vehicle.isAvailable()) {
            vehicle.setAvailability(true);
            System.out.println("Vehicle returned successfully: " + vehicle.getBrand());
        } else {
            System.out.println("Vehicle with ID " + vehicleId + " is not currently rented.");
        }
    }

    public void listRentals() {
        System.out.println("Current Rentals:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equalsIgnoreCase(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();

        // Adding vehicles
        system.addVehicle(new Car("C001", "Toyota", 50.0, 5));
        system.addVehicle(new Car("C002", "Honda", 60.0, 7));
        system.addVehicle(new Bike("B001", "Yamaha", 20.0, 150));
        system.addVehicle(new Bike("B002", "Suzuki", 25.0, 180));

        // Displaying available vehicles
        system.listVehicles();

        // Renting vehicles
        Customer customer1 = new Customer("Alice", "1234567890");
        system.rentVehicle("C001", customer1, 3);

        Customer customer2 = new Customer("Bob", "9876543210");
        system.rentVehicle("B001", customer2, 2);

        // Listing all rentals
        system.listRentals();

        // Returning a vehicle
        system.returnVehicle("C001");

        // Displaying available vehicles again
        system.listVehicles();
    }
}
