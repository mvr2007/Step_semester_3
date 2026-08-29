class StudentFeeAccount {
    private String regNo;
    private double totalFee;

    public StudentFeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }
    public final double calculateLateFee(int daysLate) {
        return this.totalFee * 0.01 * daysLate;
    }

    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(this.regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);
            System.out.println(this.regNo + " | Total Fee: Rs " + this.totalFee + " | Late Fee: Rs " + lateFee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000.0, 150000.0, 180000.0, 220000.0};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            StudentFeeAccount account = new StudentFeeAccount(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}
