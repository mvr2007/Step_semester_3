public class GymMember2 {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember2(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid Member ID");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    public static String classifyGeneration(GymMember2 member) {
        if (member instanceof EliteMember2) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember2) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember2) {
            return "Direct subclass (2 generations deep)";
        } else {
            return "Base class (1st generation)";
        }
    }

    public static int getTotalSessionsAttended(GymMember2[] members) {
        int total = 0;
        for (GymMember2 member : members) {
            if (member != null) {
                total += member.getSessionsAttended(); // Polymorphic call
            }
        }
        return total;
    }
}

class PremiumMember2 extends GymMember2 {
    private String trainerName;

    public PremiumMember2(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember2 extends PremiumMember2 {
    private String lockerNumber;

    public EliteMember2(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMember2 extends GymMember2 {
    private String className;

    public GroupClassMember2(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}
