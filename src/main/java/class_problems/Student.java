public class Student {
    private String name;
    private int attendance;

    private static String collegeName = "SRM Institute of Science and Technology";
    private static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        new Student("Ravi", 85);
        new Student("Anitha", 92);

        Student.printCollegeInfo();
    }
}
