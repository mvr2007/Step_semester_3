import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[Email → %s] %s\n", student.getName(), notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[SMS → %s] %s\n", student.getName(), notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[App → %s] %s\n", student.getName(), notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels = new ArrayList<>();

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments != null ? new ArrayList<>(targetDepartments) : new ArrayList<>();
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return;
        }

        if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        String deptsStr = String.join(", ", notice.getTargetDepartments());
        System.out.printf("Notice '%s' posted to %s.\n", notice.getTitle(), deptsStr);

        for (Student student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        Notice notice1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice(notice1);

        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        Notice notice3 = new Notice("Sports Day", Collections.emptyList());
        noticeBoard.postNotice(notice3);
    }
}
