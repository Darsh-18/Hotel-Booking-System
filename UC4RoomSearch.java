import java.util.HashMap;

/**
 * UC4 - Room Search & Availability Check
 * 
 * Demonstrates read-only access to inventory and filtering logic
 * 
 * @author Darsh
 * @version 4.0
 */

// Room class (simplified)
class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: " + price);
    }
}

// Inventory (same concept from UC3)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("===== Available Rooms v4.0 =====");

        // Room objects (domain)
        Room single = new Room("Single Room", 1, 2000);
        Room doubleRoom = new Room("Double Room", 2, 3500);
        Room suite = new Room("Suite Room", 3, 6000);

        // Check availability (read-only)
        if (inventory.getAvailability("Single Room") > 0) {
            System.out.println("\n--- Available ---");
            single.displayDetails();
        }

        if (inventory.getAvailability("Double Room") > 0) {
            System.out.println("\n--- Available ---");
            doubleRoom.displayDetails();
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            System.out.println("\n--- Available ---");
            suite.displayDetails();
        }
    }
}

// Main class
public class UC4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        // Perform search (read-only)
        searchService.searchAvailableRooms(inventory);
    }
}