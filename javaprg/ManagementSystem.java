import java.util.*;

// Booking class
class Booking {
    private String customerName;
    private String pickupLocation;
    private String dropLocation;

    public Booking(String customerName, String pickup, String drop) {
        this.customerName = customerName;
        this.pickupLocation = pickup;
        this.dropLocation = drop;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public String toString() {
        return "Customer: " + customerName + " | From: " + pickupLocation + " → To: " + dropLocation;
    }
}

// Driver class
class Driver {
    private String name;
    private boolean available;

    public Driver(String name) {
        this.name = name;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void assign() {
        available = false;
    }

    public void release() {
        available = true;
    }
}

// ✅ Main class (updated name)
public class ManagementSystem {
    private static List<Booking> bookings = new ArrayList<>();
    private static List<Driver> drivers = new ArrayList<>();

    public static void main(String[] args) {
        // Sample drivers
        drivers.add(new Driver("Ravi"));
        drivers.add(new Driver("Sunil"));
        drivers.add(new Driver("Kiran"));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== CAB MANAGEMENT SYSTEM ===");
            System.out.println("1. Book a Cab");
            System.out.println("2. View All Bookings");
            System.out.println("3. Assign Driver");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Pickup Location: ");
                    String pickup = sc.nextLine();
                    System.out.print("Enter Drop Location: ");
                    String drop = sc.nextLine();
                    bookings.add(new Booking(name, pickup, drop));
                    System.out.println("✅ Cab Booked Successfully!");
                    break;

                case 2:
                    System.out.println("📋 All Bookings:");
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        for (int i = 0; i < bookings.size(); i++) {
                            System.out.println((i + 1) + ". " + bookings.get(i));
                        }
                    }
                    break;

                case 3:
                    if (bookings.isEmpty()) {
                        System.out.println("❌ No bookings to assign.");
                        break;
                    }
                    System.out.println("🚗 Assigning drivers to bookings...");
                    for (int i = 0; i < bookings.size(); i++) {
                        Booking b = bookings.get(i);
                        Driver availableDriver = getAvailableDriver();
                        if (availableDriver != null) {
                            availableDriver.assign();
                            System.out.println("Booking " + (i + 1) + ": " + b + " | Assigned to Driver: " + availableDriver.getName());
                        } else {
                            System.out.println("Booking " + (i + 1) + ": " + b + " | No drivers available!");
                        }
                    }
                    break;

                case 4:
                    System.out.println("👋 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    private static Driver getAvailableDriver() {
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                return d;
            }
        }
        return null;
    }
}