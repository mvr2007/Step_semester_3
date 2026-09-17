public class LibraryMember3 {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember3(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }

    public static String batchPrint(LibraryMember3[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember3 member : members) {
            report.append(member.displayInfo());

            // Safely downcast using instanceof before casting
            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }
}

class StudentMember extends LibraryMember3 {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}
