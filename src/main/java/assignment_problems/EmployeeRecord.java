public class EmployeeRecord {
    private String empName;
    private double salary;

    private static String companyName = "Bright Horizon Technologies";
    private static int employeeCount = 0;

    public EmployeeRecord(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        new EmployeeRecord("Aarav", 50000);
        new EmployeeRecord("Bhavna", 62000);
        new EmployeeRecord("Chetan", 48000);

        EmployeeRecord.printCompanyInfo();
    }
}
