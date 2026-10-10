import java.util.*;

abstract class Staff {
    protected String name;
    protected String type;

    public Staff(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public abstract long calculatePay();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    private long salary;

    public FullTime(String name, long salary) {
        super(name, "FullTime");
        this.salary = salary;
    }

    @Override
    public long calculatePay() {
        return salary;
    }
}

class PartTime extends Staff {
    private long hours;
    private long rate;

    public PartTime(String name, long hours, long rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public long calculatePay() {
        return hours * rate;
    }
}

class Intern extends Staff {
    private long stipend;

    public Intern(String name, long stipend) {
        super(name, "Intern");
        this.stipend = stipend;
    }

    @Override
    public long calculatePay() {
        return stipend;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (scanner.hasNext()) {
            String type = scanner.next();
            if (type.equals("FullTime")) {
                String name = scanner.next();
                long salary = scanner.nextLong();
                staffList.add(new FullTime(name, salary));
            } else if (type.equals("PartTime")) {
                String name = scanner.next();
                long hours = scanner.nextLong();
                long rate = scanner.nextLong();
                staffList.add(new PartTime(name, hours, rate));
            } else if (type.equals("Intern")) {
                String name = scanner.next();
                long stipend = scanner.nextLong();
                staffList.add(new Intern(name, stipend));
            }
        }

        long totalPay = 0;
        long maxPay = -1;
        String topEarner = "";

        for (Staff s : staffList) {
            System.out.println(s);
            long pay = s.calculatePay();
            totalPay += pay;
            if (pay > maxPay) {
                maxPay = pay;
                topEarner = s.getName();
            }
        }

        System.out.println("Total " + totalPay);
        System.out.println("top earner " + topEarner);
    }
}
