class HostelFeeAccount extends FeeAccount {
}

class PaymentProcessor {
    private int hostelAccountsProcessed = 0;
    private int dayScholarAccountsProcessed = 0;

    public void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelAccountsProcessed++;
        } else if (account instanceof FeeAccount) {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarAccountsProcessed++;
        }
    }

    public void printSummary() {
        System.out.println("Hostel accounts processed: " + hostelAccountsProcessed +
                           " | Day-scholar accounts processed: " + dayScholarAccountsProcessed);
    }
}

public class FeeAccount {
    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        PaymentProcessor processor = new PaymentProcessor();
        double amount = 60000.0;

        for (FeeAccount acc : accounts) {
            processor.processPayment(acc, amount);
        }

        processor.printSummary();
    }
}