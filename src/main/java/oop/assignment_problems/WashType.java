import java.util.*;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCharge() { return charge; }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick", 30, 20.0);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal", 45, 30.0);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy", 60, 45.0);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class WashingMachine {
    private String id;
    private boolean isBusy;

    public WashingMachine(String id) {
        this.id = id;
        this.isBusy = false;
    }

    public String getId() { return id; }
    public boolean isBusy() { return isBusy; }

    void setBusy(boolean busy) {
        this.isBusy = busy;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;
    private boolean completed;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
        this.completed = false;
    }

    public Student getStudent() { return student; }
    public WashingMachine getMachine() { return machine; }
    public WashType getWashType() { return washType; }

    public void start() {
        machine.setBusy(true);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.\n",
                washType.getName(), machine.getId(), student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
    }

    public void complete() {
        this.completed = true;
        machine.setBusy(false);
        System.out.printf("%s cycle completed. %s is now free.\n", machine.getId(), machine.getId());
    }
}

class LaundrySystem {
    public WashCycle startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.printf("Machine %s is currently busy.\n", machine.getId());
            return null;
        }
        WashCycle cycle = new WashCycle(student, machine, washType);
        cycle.start();
        return cycle;
    }

    public void completeWash(WashCycle cycle) {
        if (cycle != null) {
            cycle.complete();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LaundrySystem system = new LaundrySystem();

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashCycle cycle1 = system.startWash(asha, m1, new QuickWash());
        WashCycle cycle2 = system.startWash(ravi, m1, new HeavyWash());
        WashCycle cycle3 = system.startWash(ravi, m2, new HeavyWash());

        system.completeWash(cycle1);

        WashCycle cycle4 = system.startWash(neha, m1, new NormalWash());
    }
}
