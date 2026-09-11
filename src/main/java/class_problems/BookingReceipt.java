import java.util.Arrays;
public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;
    private final boolean isGroup;
    private final int groupSize;
    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this(bookingId, seatNumbers, false, 1);
    }
    public BookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        this(bookingId, seatNumbers, true, groupSize);
    }
    private BookingReceipt(String bookingId, String[] seatNumbers, boolean isGroup, int groupSize) {
        this.bookingId = bookingId;
        this.isGroup = isGroup;
        this.groupSize = groupSize;
        if (seatNumbers != null) {
            this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
        } else {
            this.seatNumbers = new String[0];
        }
    }
    public String getBookingId() {
        return this.bookingId;
    }
    public String[] getSeatNumbers() {
        return Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
    }
    public boolean isGroup() {
        return this.isGroup;
    }
    public int getGroupSize() {
        return this.groupSize;
    }
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= this.seatNumbers.length) {
            throw new IndexOutOfBoundsException("Invalid seat index: " + index);
        }
        String[] newSeats = Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
        newSeats[index] = newSeat;
        return new BookingReceipt(this.bookingId, newSeats, this.isGroup, this.groupSize);
    }
    public static String processNightlySettlement(BookingReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;
        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (receipt.isGroup()) {
                groupCount++;
            } else {
                individualCount++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }
    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println("Defensive copy preserved: " + b.getSeatNumbers()[0]); 

        // Wither verification
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original: " + Arrays.toString(b.getSeatNumbers()));        
        System.out.println("Updated:  " + Arrays.toString(updated.getSeatNumbers()));
        BookingReceipt[] batch = new BookingReceipt[]{
            new BookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
    }
}
