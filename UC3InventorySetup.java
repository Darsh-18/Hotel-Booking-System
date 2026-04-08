import java.util.HashMap;

/**
 * UC3 - Centralized Room Inventory Management
 * 
 * Demonstrates use of HashMap for managing room availability
 * 
 * @author Darsh
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize inventory
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display all inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory v3.0 =====");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " -> Available: " + inventory.get(roomType));
        }
    }
}

// Main Class
public class UC3InventorySetup {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();
    }
}