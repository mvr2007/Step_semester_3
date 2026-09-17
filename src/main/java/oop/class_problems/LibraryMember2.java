import java.util.Arrays;

public class LibraryMember2 {
    private String memberId;
    private int borrowLimit;
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember2(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        // Defensive copy to prevent external tampering
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}

class StudentMember extends LibraryMember2 {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        // Halve the fine amount and forward to superclass
        super.chargeFine(amount / 2);
    }
}
