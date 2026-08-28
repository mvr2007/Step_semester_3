public class AEmployee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;
    public AEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }
    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
    public static void main(String[] args) {
        // Create three AEmployee objects
        AEmployee emp1 = new AEmployee("Aarav", 50000);
        AEmployee emp2 = new AEmployee("Diya", 60000);
        AEmployee emp3 = new AEmployee("Rohan", 55000);
        AEmployee.printCompanyInfo();
    }
}