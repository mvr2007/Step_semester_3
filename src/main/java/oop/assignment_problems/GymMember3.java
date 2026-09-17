import java.util.Arrays;

public class GymMember3 {
    private String memberId;
    private int monthlyFee;
    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    public GymMember3(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        // Return defensive copy to prevent external mutation
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }
}

class PremiumMember3 extends GymMember3 {
    private String trainerName;

    public PremiumMember3(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        // Halves the fee and delegates array insertion to parent
        super.chargeLateFee(amount / 2);
    }
}
