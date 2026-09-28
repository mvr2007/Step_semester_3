import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

enum SubmissionStatus { SUBMITTED, GRADED }

abstract class Assignment {
    private String title;
    private double maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, double maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public double getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    public abstract double calculateFinalMarks(double rawMarks, long daysLate);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penaltyFactor = Math.max(0, 1.0 - (daysLate * 0.10));
        return rawMarks * penaltyFactor;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penaltyFactor = Math.max(0, 1.0 - (daysLate * 0.20));
        return rawMarks * penaltyFactor;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;

        long daysLate = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
        if (daysLate <= 0) {
            System.out.printf("%s's submission for '%s' received (on time). Status: %s.\n",
                    student.getName(), assignment.getTitle(), status);
        } else {
            System.out.printf("%s's submission for '%s' received (%d days late). Status: %s.\n",
                    student.getName(), assignment.getTitle(), daysLate, status);
        }
    }

    public Student getStudent() { return student; }
    public Assignment getAssignment() { return assignment; }
    public SubmissionStatus getStatus() { return status; }

    public void grade(double rawMarks) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.\n", assignment.getTitle());
            return;
        }

        long daysLate = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate));
        this.finalMarks = assignment.calculateFinalMarks(rawMarks, daysLate);
        this.status = SubmissionStatus.GRADED;

        if (daysLate <= 0) {
            System.out.printf("%s graded: %.0f/%.0f. Status: %s.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks(), status);
        } else {
            long penaltyPercentage = (long) (((rawMarks - finalMarks) / rawMarks) * 100);
            System.out.printf("%s graded: %.0f/%.0f after %d%% late penalty. Status: %s.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks(), penaltyPercentage, status);
        }
    }

    public void resubmit(LocalDate newSubmissionDate) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.\n", assignment.getTitle());
            return;
        }
        this.submissionDate = newSubmissionDate;
        this.status = SubmissionStatus.SUBMITTED;
    }
}

public class Main {
    public static void main(String[] args) {
        Assignment codingLab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment essay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSub = new Submission(asha, codingLab, LocalDate.of(2026, 3, 10));
        Submission raviSub = new Submission(ravi, essay, LocalDate.of(2026, 3, 14));

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.resubmit(LocalDate.of(2026, 3, 11));
    }
}
