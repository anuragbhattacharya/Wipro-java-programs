import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Room {
    private String roomNumber;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(String roomNumber, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract String getRoomType();

    @Override
    public String toString() {
        return "Room Type: " + getRoomType() + ", Room Number: " + roomNumber +
                ", Price: $" + pricePerNight + "/night, Available: " + (isAvailable ? "Yes" : "No");
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Standard Room";
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Deluxe Room";
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

class Booking {
    private Room room;
    private Customer customer;
    private int numberOfNights;

    public Booking(Room room, Customer customer, int numberOfNights) {
        this.room = room;
        this.customer = customer;
        this.numberOfNights = numberOfNights;
    }

    public double calculateTotalCost() {
        return room.getPricePerNight() * numberOfNights;
    }

    @Override
    public String toString() {
        return "Booking: " + room.getRoomType() + " [" + room.getRoomNumber() + "] booked by " + customer.getName() +
                ", Nights: " + numberOfNights + ", Total Cost: $" + calculateTotalCost();
    }
}

class HotelBookingSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println(room.getRoomType() + " added: Room " + room.getRoomNumber());
    }

    public void listAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void bookRoom(String roomNumber, Customer customer, int numberOfNights) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            Booking booking = new Booking(room, customer, numberOfNights);
            bookings.add(booking);
            System.out.println("Room booked successfully:\n" + booking);
        } else {
            System.out.println("Room with number " + roomNumber + " is not available for booking.");
        }
    }

    public void checkOutRoom(String roomNumber) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null && !room.isAvailable()) {
            room.setAvailable(true);
            System.out.println("Room checked out successfully: " + room.getRoomNumber());
        } else {
            System.out.println("Room with number " + roomNumber + " is not currently booked.");
        }
    }

    public void listBookings() {
        System.out.println("Current Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private Room findRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();

        // Adding rooms
        system.addRoom(new StandardRoom("101", 100.0));
        system.addRoom(new StandardRoom("102", 120.0));
        system.addRoom(new DeluxeRoom("201", 200.0));
        system.addRoom(new DeluxeRoom("202", 250.0));

        // Displaying available rooms
        system.listAvailableRooms();

        // Booking a room
        Customer customer1 = new Customer("Alice", "1234567890");
        system.bookRoom("101", customer1, 3);

        Customer customer2 = new Customer("Bob", "9876543210");
        system.bookRoom("201", customer2, 2);

        // Listing all bookings
        system.listBookings();

        // Checking out a room
        system.checkOutRoom("101");

        // Displaying available rooms after checkout
        system.listAvailableRooms();
    }
}
