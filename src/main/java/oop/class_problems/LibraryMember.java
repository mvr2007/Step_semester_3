import java.util.ArrayList;
import java.util.List;
class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;
    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be non-blank and at least 4 characters long.");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolledCount = 0;
        int rejectedCount = 0;
        for (String id : memberIds) {
              try {
                  LibraryMember member = new LibraryMember(id, borrowLimit);
                  enrolledCount++;
              }catch (IllegalArgumentException e) {
                  rejectedCount++;
              }
        }
        return "Enrolled: " + enrolledCount + " | Rejected: " + rejectedCount;
    }
}
class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    public String getCourse() {
        return course;
    }
}
