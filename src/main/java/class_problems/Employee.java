public class Employee {
    private String empId;
    private double salary;

    public Employee(String empId, double salary) {
        // Resolving field vs parameter name shadowing
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        // 'salary' refers to the parameter, 'this.salary' refers to the instance variable
        this.salary = this.salary + salary;
    }

    public void printFinalSalary() {
        System.out.println(this.empId + " | Final Salary: Rs " + this.salary);
    }

    public static void main(String[] args) {
        double[] startingSalaries = {40000, 55000, 62000, 48000};
        Employee[] employees = new Employee[startingSalaries.length];

        for (int i = 0; i < startingSalaries.length; i++) {
            employees[i] = new Employee("E-10" + (i + 1), startingSalaries[i]);
        }

        double festivalBonus = 5000.0;
        for (Employee emp : employees) {
            emp.raiseSalary(festivalBonus);
            emp.printFinalSalary();
        }
    }
}
