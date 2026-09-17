public class GymMember4 {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember4(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }

    public static String batchPrint(GymMember4[] members) {
        StringBuilder announcement = new StringBuilder();

        for (GymMember4 member : members) {
            announcement.append(member.displayInfo());

            // Safely downcast using instanceof check
            if (member instanceof PremiumMember4) {
                PremiumMember4 premium = (PremiumMember4) member;
                announcement.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }
}

class PremiumMember4 extends GymMember4 {
    private String trainerName;

    public PremiumMember4(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}public class GymMember4 {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember4(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }

    public static String batchPrint(GymMember4[] members) {
        StringBuilder announcement = new StringBuilder();

        for (GymMember4 member : members) {
            announcement.append(member.displayInfo());

            // Safely downcast using instanceof check
            if (member instanceof PremiumMember4) {
                PremiumMember4 premium = (PremiumMember4) member;
                announcement.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }
}

class PremiumMember4 extends GymMember4 {
    private String trainerName;

    public PremiumMember4(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}
