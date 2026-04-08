import java.io.*;
import java.util.*;

/**
 * UC12 - Data Persistence & System Recovery
 * 
 * Demonstrates serialization and deserialization
 * 
 * @author Darsh
 * @version 12.0
 */

// Reservation (Serializable)
class Reservation implements Serializable {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println(guestName + " | " + roomType + " | " + roomId);
    }
}

// Inventory (Serializable)
class RoomInventory implements Serializable {
    Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_data.ser";

    // Save data
    public static void save(RoomInventory inventory, List<Reservation> bookings) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            oos.writeObject(bookings);

            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public static Object[] load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> bookings = (List<Reservation>) ois.readObject();

            System.out.println("Data loaded successfully.");

            return new Object[]{inventory, bookings};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main class
public class UC12DataPersistenceRecovery {

    public static void main(String[] args) {

        RoomInventory inventory;
        List<Reservation> bookings;

        // Try loading data
        Object[] data = PersistenceService.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            bookings = new ArrayList<>();

            // Add sample data
            bookings.add(new Reservation("Darsh", "Single Room", "SR_101"));
        }

        // Display current state
        inventory.display();

        System.out.println("\nBookings:");
        for (Reservation r : bookings) {
            r.display();
        }

        // Save state before exit
        PersistenceService.save(inventory, bookings);
    }
}