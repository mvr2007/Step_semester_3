class FeeAccount {
    public void pay() {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay() {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class BatchProcessor {
    private int hostelCount = 0;
    private int dayScholarCount = 0;

    public void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            hostelCount++;
            account.pay();
        } else if (account instanceof FeeAccount) {
            dayScholarCount++;
            account.pay();
        }
    }

    public void printBatchSummary() {
        System.out.println("Hostel accounts processed: " + hostelCount +
                " | Day-scholar accounts processed: " + dayScholarCount);
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        BatchProcessor processor = new BatchProcessor();
        double paymentAmount = 60000.0;

        for (FeeAccount acc : accounts) {
            processor.processPayment(acc, paymentAmount);
        }

        processor.printBatchSummary();
    }
}
