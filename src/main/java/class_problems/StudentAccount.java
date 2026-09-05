public class StudentAccount {
    private String regNo;
    private double totalFee;

    // Fixed daily late fee rate (e.g., 1% of total fee per late day)
    private static final double DAILY_LATE_FEE_RATE = 0.01;

    public StudentAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // Locked against override
    public final double calculateLateFee(int daysLate) {
        return this.totalFee * DAILY_LATE_FEE_RATE * daysLate;
    }

    // Locked against override
    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(this.regNo + " - On time, no late fee");
        } else {
            double fee = calculateLateFee(daysLate);
            System.out.println(this.regNo + " | Total Fee: Rs " + this.totalFee + " | Late Fee: Rs " + fee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            StudentAccount acc = new StudentAccount(regNos[i], totalFees[i]);
            acc.printSummary(daysLate[i]);
        }
    }
}
