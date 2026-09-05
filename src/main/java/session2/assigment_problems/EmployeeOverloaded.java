public class EmployeeOverloaded {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    public EmployeeOverloaded(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    public EmployeeOverloaded(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        EmployeeOverloaded permanent = new EmployeeOverloaded("E-101", "Divya", 65000);
        EmployeeOverloaded intern = new EmployeeOverloaded("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}
