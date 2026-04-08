import java.util.*;

/**
 * UC10 - Booking Cancellation & Inventory Rollback
 * 
 * Demonstrates rollback using Stack
 * 
 * @author Darsh
 * @version 10.0
 */

// Reservation
class Reservation {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increase(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> bookings;
    private Stack<String> rollbackStack;

    public CancellationService() {
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    // Add confirmed booking
    public void addBooking(Reservation r) {
        bookings.put(r.roomId, r);
    }

    // Cancel booking
    public void cancel(String roomId, RoomInventory inventory) {

        if (!bookings.containsKey(roomId)) {
            System.out.println("Cancellation Failed: Booking not found");
            return;
        }

        Reservation r = bookings.get(roomId);

        // Push to stack (rollback tracking)
        rollbackStack.push(roomId);

        // Restore inventory
        inventory.increase(r.roomType);

        // Remove booking
        bookings.remove(roomId);

        System.out.println("Booking Cancelled: " + roomId);
    }

    // Show rollback stack
    public void showRollbackStack() {
        System.out.println("\nRollback Stack:");
        for (String id : rollbackStack) {
            System.out.println(id);
        }
    }
}

// Main class
public class UC10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulated bookings
        service.addBooking(new Reservation("Darsh", "Single Room", "SR_101"));
        service.addBooking(new Reservation("Aman", "Double Room", "DR_201"));

        // Cancel booking
        service.cancel("SR_101", inventory);

        // Show inventory
        inventory.display();

        // Show rollback history
        service.showRollbackStack();

        // Invalid cancellation
        service.cancel("XX_999", inventory);
    }
}