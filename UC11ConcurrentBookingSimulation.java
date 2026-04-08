import java.util.*;

/**
 * UC11 - Concurrent Booking Simulation
 * 
 * Demonstrates thread safety using synchronization
 * 
 * @author Darsh
 * @version 11.0
 */

// Reservation
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Inventory
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1); // only 1 room
    }

    // synchronized method
    public synchronized boolean allocate(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }
}

// Booking Task (Thread)
class BookingTask implements Runnable {

    private RoomInventory inventory;
    private Reservation reservation;

    public BookingTask(RoomInventory inventory, Reservation reservation) {
        this.inventory = inventory;
        this.reservation = reservation;
    }

    @Override
    public void run() {

        boolean success = inventory.allocate(reservation.roomType);

        if (success) {
            System.out.println(Thread.currentThread().getName() +
                    " SUCCESS: " + reservation.guestName + " booked " + reservation.roomType);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED: " + reservation.guestName + " - No rooms left");
        }
    }
}

// Main class
public class UC11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Multiple users trying to book same room
        Thread t1 = new Thread(new BookingTask(inventory, new Reservation("Darsh", "Single Room")));
        Thread t2 = new Thread(new BookingTask(inventory, new Reservation("Aman", "Single Room")));
        Thread t3 = new Thread(new BookingTask(inventory, new Reservation("Riya", "Single Room")));

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}