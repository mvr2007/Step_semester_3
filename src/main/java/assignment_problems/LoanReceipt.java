import java.util.Arrays;
public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;
    private final boolean referenceOnly;
    private final String roomNumber;
    private static final String BRANCH_LEDGER_CODE;
    static {
        BRANCH_LEDGER_CODE = "PTL-CENTRAL-01";
    }
    public LoanReceipt(String memberId, String[] bookIds) {
        this(memberId, bookIds, false, null);
    }
    public LoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        this(memberId, bookIds, true, roomNumber);
    }
    private LoanReceipt(String memberId, String[] bookIds, boolean referenceOnly, String roomNumber) {
        this.memberId = memberId;
        this.referenceOnly = referenceOnly;
        this.roomNumber = roomNumber;
        if (bookIds != null) {
            this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        } else {
            this.bookIds = new String[0];
        }
    }
    public String getMemberId() {
        return this.memberId;
    }

    public boolean isReferenceOnly() {
        return this.referenceOnly;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public static String getBranchLedgerCode() {
        return BRANCH_LEDGER_CODE;
    }

    // Defensive copy on read prevents external array tampering
    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    // "Wither" pattern: creates and returns a brand-new instance, leaving original intact
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= this.bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid book index: " + index);
        }
        String[] newIds = Arrays.copyOf(this.bookIds, this.bookIds.length);
        newIds[index] = newId;
        return new LoanReceipt(this.memberId, newIds, this.referenceOnly, this.roomNumber);
    }

    // Factory helper method matching the ReferenceOnlyLoanReceipt signature
    public static LoanReceipt createReferenceOnly(String memberId, String[] bookIds, String roomNumber) {
        return new LoanReceipt(memberId, bookIds, true, roomNumber);
    }

    // Nightly circulation reconciler with null-safety and category dispatch
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int refCount = 0;
        int regCount = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt.isReferenceOnly()) {
                refCount++;
            } else {
                regCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + refCount + " reference-only | " + regCount + " regular";
    }

    public static void main(String[] args) {
        // 1. Verify Defensive Copying on Getter
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Preserved ID after mutation: " + r.getBookIds()[0]); // BK-100

        // 2. Verify with-style method
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original:  " + Arrays.toString(r.getBookIds()));        // [BK-100, BK-101]
        System.out.println("Corrected: " + Arrays.toString(corrected.getBookIds())); // [BK-100, BK-102]

        // 3. Verify Nightly Circulation Settlement with nulls
        LoanReceipt[] batch = new LoanReceipt[]{
            createReferenceOnly("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(batch));
        // Output: "2 processed | 1 null skipped | 1 reference-only | 1 regular"
    }
}
