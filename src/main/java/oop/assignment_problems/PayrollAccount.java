public class PayrollAccount {
    private double basicSalary;
    private double bonus;
    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Setting basic salary to 0.0");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0;
    }
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid bonus amount. Amount must be greater than 0.");
        } else {
            this.bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percentage. Must be between 0 and 100.");
        } else {
            double taxAmount = (this.basicSalary * percent) / 100.0;
            this.basicSalary -= taxAmount;
            System.out.println("Tax deducted: " + (int) percent + "%");
        }
    }
    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);
        account.deductTax(10);

        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}