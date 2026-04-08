import java.util.*;

/**
 * UC8 - Booking History & Reporting
 * 
 * Demonstrates storing and reporting booking history
 * 
 * @author Darsh
 * @version 8.0
 */

// Reservation class
class Reservation {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println("Guest: " + guestName +
                " | Room: " + roomType +
                " | Room ID: " + roomId);
    }
}

// Booking History
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add booking
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return history;
    }
}

// Report Service
class BookingReportService {

    public void generateReport(List<Reservation> bookings) {

        System.out.println("===== Booking History Report =====");

        for (Reservation r : bookings) {
            r.display();
        }

        System.out.println("\nTotal Bookings: " + bookings.size());
    }
}

// Main class
public class UC8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulated confirmed bookings
        history.addReservation(new Reservation("Darsh", "Single Room", "SR_101"));
        history.addReservation(new Reservation("Aman", "Double Room", "DR_201"));
        history.addReservation(new Reservation("Riya", "Suite Room", "SU_301"));

        // Generate report
        BookingReportService report = new BookingReportService();
        report.generateReport(history.getAllBookings());
    }
}