import java.time.LocalDateTime;
import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 400.0; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Show {
    private String movieName;
    private LocalDateTime startTime;
    private Set<String> bookedSeatNumbers = new HashSet<>();

    public Show(String movieName, LocalDateTime startTime) {
        this.movieName = movieName;
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() { return startTime; }

    public boolean isSeatAvailable(String seatNumber) {
        return !bookedSeatNumbers.contains(seatNumber);
    }

    public void reserveSeat(String seatNumber) {
        bookedSeatNumbers.add(seatNumber);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeatNumbers.remove(seatNumber);
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean isCancelled;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.isCancelled = false;
    }

    public Customer getCustomer() { return customer; }
    public List<Seat> getSeats() { return seats; }
    public boolean isCancelled() { return isCancelled; }

    public double calculateTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }

    public void cancel(LocalDateTime currentTime) {
        if (currentTime.isAfter(show.getStartTime()) || currentTime.isEqual(show.getStartTime())) {
            System.out.println("Cannot cancel booking: Show has already started.");
            return;
        }

        this.isCancelled = true;
        List<String> seatNums = new ArrayList<>();
        for (Seat seat : seats) {
            show.releaseSeat(seat.getSeatNumber());
            seatNums.add(seat.getSeatNumber());
        }

        System.out.printf("%s's booking cancelled. Seats %s released.\n",
                customer.getName(), String.join(", ", seatNums));
    }
}

class TicketCounterService {
    public Booking bookTickets(Customer customer, Show show, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Cannot book more than 6 seats per transaction.");
            return null;
        }

        for (Seat seat : seats) {
            if (!show.isSeatAvailable(seat.getSeatNumber())) {
                System.out.printf("Seat %s is already booked for this show.\n", seat.getSeatNumber());
                return null;
            }
        }

        List<String> seatNums = new ArrayList<>();
        for (Seat seat : seats) {
            show.reserveSeat(seat.getSeatNumber());
            seatNums.add(seat.getSeatNumber());
        }

        Booking booking = new Booking(customer, show, seats);
        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.\n",
                customer.getName(), String.join(", ", seatNums), booking.calculateTotal());
        return booking;
    }
}

public class Main {
    public static void main(String[] args) {
        TicketCounterService service = new TicketCounterService();
        LocalDateTime showTime = LocalDateTime.of(2026, 3, 28, 19, 0);
        Show show = new Show("Movie Show 7 PM", showTime);

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        Booking ashaBooking = service.bookTickets(asha, show, Arrays.asList(a1, a2, f5));

        Seat a2Dup = new RegularSeat("A2");
        service.bookTickets(ravi, show, Arrays.asList(a2Dup));

        Booking raviBooking = service.bookTickets(ravi, show, Arrays.asList(r1));

        LocalDateTime currentTime = LocalDateTime.of(2026, 3, 28, 18, 0);
        if (ashaBooking != null) {
            ashaBooking.cancel(currentTime);
        }

        Seat a2New = new RegularSeat("A2");
        service.bookTickets(neha, show, Arrays.asList(a2New));
    }
}
