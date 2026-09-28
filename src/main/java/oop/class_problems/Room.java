import java.time.LocalDate;
import java.util.*;

// --- Room Abstraction ---
abstract class Room {
    private String roomNumber;
    private double basePricePerNight;

    public Room(String roomNumber, double basePricePerNight) {
        this.roomNumber = roomNumber;
        this.basePricePerNight = basePricePerNight;
    }

    public String getRoomNumber() { return roomNumber; }
    public double getBasePricePerNight() { return basePricePerNight; }

    public abstract double calculatePrice(int nights);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * getBasePricePerNight();
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * getBasePricePerNight() * 1.2; // 20% premium
    }
}

// --- Customer & Reservation ---
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }
}

class Reservation {
    private String reservationId;
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    public Reservation(String reservationId, Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        int nights = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        this.price = room.calculatePrice(nights);
    }

    public Room getRoom() { return room; }
    public Customer getCustomer() { return customer; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getPrice() { return price; }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return (start.isBefore(endDate) && end.isAfter(startDate));
    }
}

// --- Booking Service ---
class HotelBookingService {
    private List<Reservation> activeReservations = new ArrayList<>();

    public boolean checkAvailability(Room room, LocalDate start, LocalDate end) {
        for (Reservation res : activeReservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public Reservation createReservation(Customer customer, Room room, LocalDate start, LocalDate end) {
        if (!checkAvailability(room, start, end)) {
            System.out.println(room.getRoomNumber() + " is not available from " + start + " to " + end + ".");
            return null;
        }

        Reservation res = new Reservation(UUID.randomUUID().toString(), customer, room, start, end);
        activeReservations.add(res);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getRoomNumber() + 
                           " (" + start + " to " + end + "). Price: $" + res.getPrice());
        return res;
    }

    public void cancelReservation(Reservation res) {
        if (res != null && activeReservations.contains(res)) {
            activeReservations.remove(res);
            System.out.println("Reservation for " + res.getCustomer().getName() + ", " + res.getRoom().getRoomNumber() + 
                               " (" + res.getStartDate() + " to " + res.getEndDate() + ") cancelled successfully.");
        }
    }
}

// --- Main Execution ---
public class Main {
    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();

        StandardRoom room101 = new StandardRoom("Standard Room 101", 100.0);
        DeluxeRoom room201 = new DeluxeRoom("Deluxe Room 201", 150.0);

        Customer customerA = new Customer("CA", "Customer A");
        Customer customerB = new Customer("CB", "Customer B");
        Customer customerC = new Customer("CC", "Customer C");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);

        // 1. Check availability
        if (service.checkAvailability(room101, jan1, jan5)) {
            System.out.println("Standard Room 101 is available from " + jan1 + " to " + jan5 + ".");
        }

        // 2. Customer A reserves Room 101
        Reservation resA = service.createReservation(customerA, room101, jan1, jan5);

        // 3. Customer B attempts to reserve overlapping dates
        service.createReservation(customerB, room101, jan3, jan7);

        // 4. Customer A cancels reservation
        service.cancelReservation(resA);

        // 5. Customer C reserves Deluxe Room 201
        service.createReservation(customerC, room201, feb10, feb12);
    }
}
