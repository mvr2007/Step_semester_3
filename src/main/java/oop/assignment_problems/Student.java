import java.util.*;

class Student {
    private String rollNumber;
    private String name;

    public Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollNumber, student.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Student> members = new HashSet<>();
        List<String> containsResults = new ArrayList<>();
        boolean printedCount = false;

        while (scanner.hasNext()) {
            String op = scanner.next();
            if (op.equals("ADD")) {
                String roll = scanner.next();
                String name = scanner.next();
                Student s = new Student(roll, name);
                if (members.add(s)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (op.equals("CONTAINS")) {
                if (!printedCount) {
                    System.out.println("member count " + members.size());
                    printedCount = true;
                }
                String roll = scanner.next();
                String name = scanner.next();
                Student s = new Student(roll, name);
                if (members.contains(s)) {
                    System.out.println("contains: true");
                } else {
                    System.out.println("contains: false");
                }
            }
        }
        if (!printedCount) {
            System.out.println("member count " + members.size());
        }
    }
}
