public class LibraryMember4 {
    private static int totalMembers = 0;
    private final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember4(int borrowLimit) {
        totalMembers++;
        this.memberNumber = "LIB-" + (100 + totalMembers);
        this.borrowLimit = borrowLimit;
    }

    public LibraryMember4(String memberId, int borrowLimit) {
        this(borrowLimit); // Delegates to primary constructor
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static int getMembersEnrolled() {
        return totalMembers;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    // Overloaded borrowBook method delegating to no-arg version
    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R' &&
               Character.isDigit(code.charAt(1)) &&
               Character.isDigit(code.charAt(2)) &&
               Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(LibraryMember4[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members != null) {
            for (LibraryMember4 member : members) {
                if (member == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                if (member instanceof FacultyMember) {
                    facultyCount++;
                } else {
                    regularCount++;
                }
            }
        }

        return processed + " processed | " + 
               nullSkipped + " null skipped | " + 
               facultyCount + " faculty | " + 
               regularCount + " regular";
    }
}

class FacultyMember extends LibraryMember4 {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
