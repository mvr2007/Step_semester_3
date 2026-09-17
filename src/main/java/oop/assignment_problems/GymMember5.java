public class GymMember5 {
    private static int totalMembers = 0;
    private final String membershipNumber;
    private int monthlyFee;
    private int feesPaid;

    public GymMember5(int monthlyFee) {
        totalMembers++;
        this.membershipNumber = "GYM-" + (2000 + totalMembers);
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
    }

    public GymMember5(String memberId, int monthlyFee) {
        this(monthlyFee); // Delegate to primary constructor
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public static int getMembersEnrolled() {
        return totalMembers;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    // Overloaded payFee method delegating logic to the single-argument version
    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G' &&
               Character.isDigit(code.charAt(1)) &&
               Character.isDigit(code.charAt(2)) &&
               Character.isUpperCase(code.charAt(3));
    }

    public static String processWeeklyCheckIn(GymMember5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members != null) {
            for (GymMember5 member : members) {
                if (member == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                if (member instanceof GroupClassMember5) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + 
               nullSkipped + " null skipped | " + 
               groupCount + " group | " + 
               individualCount + " individual";
    }
}

class GroupClassMember5 extends GymMember5 {
    private String className;

    public GroupClassMember5(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public GroupClassMember5(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
