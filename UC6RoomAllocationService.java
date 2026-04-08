import java.util.*;

/**
 * UC6 - Reservation Confirmation & Room Allocation
 * 
 * Demonstrates allocation using Queue, HashMap, and Set
 * 
 * @author Darsh
 * @version 6.0
 */

// Reservation (same as UC5)
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory (same idea as UC3)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decreaseAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(Queue<Reservation> queue) {
        this.queue = queue;
        this.allocatedRooms = new HashMap<>();
    }

    public void processBookings(RoomInventory inventory) {

        System.out.println("===== Processing Bookings =====");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            String roomType = r.roomType;

            // Check availability
            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique room ID
                String roomId = roomType.substring(0, 2).toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 4);

                // Ensure set exists
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());

                // Add room ID (Set ensures uniqueness)
                allocatedRooms.get(roomType).add(roomId);

                // Decrease inventory
                inventory.decreaseAvailability(roomType);

                System.out.println("Booking Confirmed: " + r.guestName +
                        " -> " + roomType + " | Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability): " + r.guestName + " -> " + roomType);
            }
        }
    }
}

// Main class
public class UC6RoomAllocationService {

    public static void main(String[] args) {

        // Queue (UC5)
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Darsh", "Single Room"));
        queue.add(new Reservation("Aman", "Single Room"));
        queue.add(new Reservation("Riya", "Single Room")); // should fail

        // Inventory (UC3)
        RoomInventory inventory = new RoomInventory();

        // Booking Service
        BookingService service = new BookingService(queue);

        // Process bookings
        service.processBookings(inventory);
    }
}