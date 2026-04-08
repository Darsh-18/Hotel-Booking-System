import java.util.*;

/**
 * UC7 - Add-On Service Selection
 * 
 * Demonstrates mapping of reservation IDs to services
 * 
 * @author Darsh
 * @version 7.0
 */

// Service class
class Service {
    String name;
    double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Service Manager
class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added " + service.name + " to Reservation: " + reservationId);
    }

    // Display services
    public void displayServices(String reservationId) {

        System.out.println("\nServices for Reservation: " + reservationId);

        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        double total = 0;

        for (Service s : services) {
            System.out.println("- " + s.name + " : ₹" + s.price);
            total += s.price;
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}

// Main class
public class UC7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "SR_1234";

        // Adding services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("WiFi", 200));
        manager.addService(reservationId, new Service("Spa", 1500));

        // Display services
        manager.displayServices(reservationId);
    }
}