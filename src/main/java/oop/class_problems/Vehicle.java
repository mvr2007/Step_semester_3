import java.util.*;
abstract class Vehicle {
    private String licensePlate;
    private String model;
    private boolean isRented;
    public Vehicle(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isRented = false;
    }
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }
    public abstract double calculateRentalCharge(int days);
}
class Sedan extends Vehicle {
    private static final double DAILY_RATE = 50.0;
    public Sedan(String licensePlate, String model) {
        super(licensePlate, model);
    }
    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}
class SUV extends Vehicle {
    private static final double DAILY_RATE = 80.0;
    public SUV(String licensePlate, String model) {
        super(licensePlate, model);
    }
    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}
class Customer {
    private String customerId;
    private String name;
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
    public String getName() { return name; }
}
class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double totalCharge;
    private boolean isActive;
    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalCharge = vehicle.calculateRentalCharge(days);
        this.isActive = true;
        this.vehicle.setRented(true);
    }
    public Vehicle getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public double getTotalCharge() { return totalCharge; }
    public boolean isActive() { return isActive; }
    public void returnVehicle() {
        this.isActive = false;
        this.vehicle.setRented(false);
    }
}
class RentalSystem {
    private Map<String, Rental> activeRentals = new HashMap<>();
    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return null;
        }
        Rental rental = new Rental(vehicle, customer, days);
        activeRentals.put(vehicle.getLicensePlate(), rental);
        System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName() + 
                           ". Rental charge: $" + rental.getTotalCharge());
        return rental;
    }
    public void returnVehicle(Rental rental) {
        if (rental != null && rental.isActive()) {
            rental.returnVehicle();
            activeRentals.remove(rental.getVehicle().getLicensePlate());
            System.out.println(rental.getVehicle().getModel() + " returned by " + rental.getCustomer().getName() + ".");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Customer c1 = new Customer("C1", "Customer 1");
        Customer c2 = new Customer("C2", "Customer 2");
        Customer c3 = new Customer("C3", "Customer 3");

        Sedan sedanA = new Sedan("SED123", "Sedan A");
        SUV suvB = new SUV("SUV456", "SUV B");
