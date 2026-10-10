import java.util.*;

interface Chargeable {
    boolean canCharge();
}

abstract class Vehicle {
    protected String passNumber;
    protected String ownerName;
    protected String type;

    public Vehicle(String type, String passNumber, String ownerName) {
        this.type = type;
        this.passNumber = passNumber;
        this.ownerName = ownerName;
    }

    public abstract int getPassFee();

    public String getPassNumber() {
        return passNumber;
    }

    public String getType() {
        return type;
    }
}

class Bike extends Vehicle {
    public Bike(String passNumber, String ownerName) {
        super("Bike", passNumber, ownerName);
    }

    @Override
    public int getPassFee() {
        return 300;
    }
}

class Car extends Vehicle {
    public Car(String passNumber, String ownerName) {
        super("Car", passNumber, ownerName);
    }

    @Override
    public int getPassFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    public EBike(String passNumber, String ownerName) {
        super("EBike", passNumber, ownerName);
    }

    @Override
    public int getPassFee() {
        return 300;
    }

    @Override
    public boolean canCharge() {
        return true;
    }
}

class ECar extends Vehicle implements Chargeable {
    public ECar(String passNumber, String ownerName) {
        super("ECar", passNumber, ownerName);
    }

    @Override
    public int getPassFee() {
        return 1000;
    }

    @Override
    public boolean canCharge() {
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (scanner.hasNext()) {
            String op = scanner.next();
            if (op.equals("PASS")) {
                String type = scanner.next();
                String passNum = scanner.next();
                String owner = scanner.next();
                Vehicle v = null;
                if (type.equalsIgnoreCase("Bike")) v = new Bike(passNum, owner);
                else if (type.equalsIgnoreCase("Car")) v = new Car(passNum, owner);
                else if (type.equalsIgnoreCase("EBike")) v = new EBike(passNum, owner);
                else if (type.equalsIgnoreCase("ECar")) v = new ECar(passNum, owner);

                if (v != null) {
                    vehicles.put(passNum, v);
                    System.out.println(passNum + " (" + type + ") pass fee " + v.getPassFee());
                }
            } else if (op.equals("CHARGE")) {
                String passNum = scanner.next();
                Vehicle v = vehicles.get(passNum);
                if (v instanceof Chargeable && ((Chargeable) v).canCharge()) {
                    System.out.println(passNum + " charging bay allotted");
                } else {
                    System.out.println(passNum + " rejected: charging unsupported");
                }
            }
        }
    }
}
