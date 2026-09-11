public class CineScreen {
    private final int seatsTotal;
    private int seatsAvailable;
    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("Total seats must be strictly greater than 0: " + seatsTotal);
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }
    public void bookSeat() {
        if (this.seatsAvailable > 0) {
            this.seatsAvailable--;
        }
    }
    public void cancelBooking() {
        if (this.seatsAvailable < this.seatsTotal) {
            this.seatsAvailable++;
        }
    }
    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }
    public int getSeatsTotal() {
        return this.seatsTotal;
    }
    public static void main(String[] args) {
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new CineScreen(0) construction rejected");
        }
        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat();
        System.out.println("Available after 3 bookings: " + c.getSeatsAvailable()); // 0
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();
        System.out.println("Available after 3 cancellations: " + c.getSeatsAvailable()); // 2
    }
}
