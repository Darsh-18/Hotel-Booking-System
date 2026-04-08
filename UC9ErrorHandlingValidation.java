import java.util.*;

/**
 * UC9 - Error Handling & Validation
 * 
 * Demonstrates validation and custom exception handling
 * 
 * @author Darsh
 * @version 9.0
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    public void decreaseAvailability(String type) throws InvalidBookingException {
        int current = getAvailability(type);

        if (current <= 0) {
            throw new InvalidBookingException("No availability for " + type);
        }

        inventory.put(type, current - 1);
    }
}

// Validator
class BookingValidator {

    private static final List<String> validRooms =
            Arrays.asList("Single Room", "Double Room", "Suite Room");

    public static void validate(String roomType) throws InvalidBookingException {

        if (!validRooms.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }
}

// Booking Service
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void bookRoom(String guestName, String roomType) {

        try {
            // Validate input
            BookingValidator.validate(roomType);

            // Allocate
            inventory.decreaseAvailability(roomType);

            System.out.println("Booking Successful: " + guestName + " -> " + roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

// Main class
public class UC9ErrorHandlingValidation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        // Valid booking
        service.bookRoom("Darsh", "Single Room");

        // Invalid room type
        service.bookRoom("Aman", "Luxury Room");

        // No availability
        service.bookRoom("Riya", "Suite Room");
    }
}