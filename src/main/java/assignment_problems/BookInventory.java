public class BookInventory {
    private final int copiesTotal;
    private int copiesAvailable;
    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("copiesTotal must be a positive integer: " + copiesTotal);
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }
    public void checkOut() {
        if (this.copiesAvailable > 0) {
            this.copiesAvailable--;
        }
    }
    public void checkIn() {
        if (this.copiesAvailable < this.copiesTotal) {
            this.copiesAvailable++;
        }
    }
    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }
    public int getCopiesTotal() {
        return this.copiesTotal;
    }
    public static void main(String[] args) {
        BookInventory b1 = new BookInventory(3);
        b1.checkOut();
        b1.checkOut();
        b1.checkOut();
        b1.checkOut(); 
        System.out.println("Available after 4 checkouts: " + b1.getCopiesAvailable()); 
        BookInventory b2 = new BookInventory(3);
        b2.checkIn();
        b2.checkIn();
        b2.checkIn();
        b2.checkIn(); 
        System.out.println("Available after 4 check-ins: " + b2.getCopiesAvailable()); 
    }
}
