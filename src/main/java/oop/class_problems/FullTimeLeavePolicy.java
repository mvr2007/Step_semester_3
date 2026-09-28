import java.time.LocalDate;

// --- Enums & States ---
enum LeaveStatus { PENDING, APPROVED, REJECTED }

// --- Leave Policy Strategy Interface ---
interface LeavePolicy {
    boolean isLeaveAllowed(Employee employee, int days);
}
class FullTimeLeavePolicy implements LeavePolicy {
    @Override
    public boolean isLeaveAllowed(Employee employee, int days) {
        return days <= 20; // Full-time policy limit
    }
}
class PartTimeLeavePolicy implements LeavePolicy {
    @Override
    public boolean isLeaveAllowed(Employee employee, int days) {
        return days <= 10; // Part-time policy limit
    }
}
// --- Employee Hierarchy ---
abstract class Employee {
    private String id;
    private String name;
    private LeavePolicy leavePolicy;
    public Employee(String id, String name, LeavePolicy leavePolicy) {
        this.id = id;
        this.name = name;
        this.leavePolicy = leavePolicy;
    }
    public String getName() { return name; }
    public LeavePolicy getLeavePolicy() { return leavePolicy; }
}
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name) {
        super(id, name, new FullTimeLeavePolicy());
    }
}
class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name) {
        super(id, name, new PartTimeLeavePolicy());
    }
}
// --- Leave Request Class ---
class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private int durationDays;
    private LeaveStatus status;
    public LeaveRequest(Employee employee, String startDate, String endDate, int durationDays) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationDays = durationDays;
        this.status = LeaveStatus.PENDING;
        System.out.println("Leave request submitted for " + employee.getName() + 
                           " (" + startDate + " to " + endDate + "). Status: " + status);
    }
    public Employee getEmployee() { return employee; }
    public LeaveStatus getStatus() { return status; }
    public void approve(Employee reviewer) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to Approved.");
            return;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + startDate + " to " + endDate + ") approved. Status: " + status);
    }
    public void reject(Employee reviewer) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to Rejected.");
            return;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + startDate + " to " + endDate + ") rejected. Status: " + status);
    }
    public void setStatus(LeaveStatus newStatus) {
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to " + newStatus + ".");
            return;
        }
        this.status = newStatus;
    }
}
// --- Main execution ---
public class Main {
    public static void main(String[] args) {
        FullTimeEmployee john = new FullTimeEmployee("E1", "John");
        PartTimeEmployee jane = new PartTimeEmployee("E2", "Jane");
        FullTimeEmployee managerAlice = new FullTimeEmployee("M1", "Alice");
        FullTimeEmployee managerBob = new FullTimeEmployee("M2", "Bob");
        // 1. John submits leave request
        LeaveRequest johnRequest = new LeaveRequest(john, "Jan 1", "Jan 5", 5);
        // 2. Manager Alice approves
        johnRequest.approve(managerAlice);
        // 3. Jane submits leave request
        LeaveRequest janeRequest = new LeaveRequest(jane, "Feb 10", "Feb 11", 2);
        // 4. Manager Bob rejects
        janeRequest.reject(managerBob);
        // 5. John attempts to revert to Pending
        johnRequest.setStatus(LeaveStatus.PENDING);
    }
}
