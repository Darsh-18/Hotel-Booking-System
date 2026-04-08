import java.util.LinkedList;
import java.util.Queue;

/**
 * UC5 - Booking Request (First-Come-First-Served)
 * 
 * Demonstrates FIFO queue for handling booking requests
 * 
 * @author Darsh
 * @version 5.0
 */

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking Request Queue
class BookingQueue {
    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request added for " + r.guestName);
    }

    // Display queue
    public void displayQueue() {
        System.out.println("\n===== Booking Queue (FIFO) =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class
public class UC5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Simulating booking requests
        bookingQueue.addRequest(new Reservation("Darsh", "Single Room"));
        bookingQueue.addRequest(new Reservation("Aman", "Double Room"));
        bookingQueue.addRequest(new Reservation("Riya", "Suite Room"));

        // Display queue
        bookingQueue.displayQueue();
    }
}