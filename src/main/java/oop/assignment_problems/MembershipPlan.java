enum MembershipStatus { ACTIVE, FROZEN, EXPIRED }

abstract class MembershipPlan {
    private String name;
    private int durationMonths;
    private static final double BASE_RATE_PER_MONTH = 1000.0;

    public MembershipPlan(String name, int durationMonths) {
        this.name = name;
        this.durationMonths = durationMonths;
    }

    public String getName() { return name; }
    public int getDurationMonths() { return durationMonths; }
    public double getBaseRate() { return BASE_RATE_PER_MONTH; }

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() { super("Monthly", 1); }
    @Override public double calculateFee() { return getBaseRate() * getDurationMonths(); }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() { super("Quarterly", 3); }
    @Override public double calculateFee() { return getBaseRate() * getDurationMonths() * 0.90; }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() { super("Annual", 12); }
    @Override public double calculateFee() { return getBaseRate() * getDurationMonths() * 0.75; }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    private double fee;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: %s.\n",
                plan.getName(), member.getName(), fee, status);
    }

    public Member getMember() { return member; }
    public MembershipStatus getStatus() { return status; }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.printf("%s checked in successfully.\n", member.getName());
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.\n", member.getName(), status);
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        if (status == MembershipStatus.ACTIVE) {
            this.status = MembershipStatus.FROZEN;
            System.out.printf("%s's membership frozen. Status: %s.\n", member.getName(), status);
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        if (status == MembershipStatus.FROZEN) {
            this.status = MembershipStatus.ACTIVE;
            System.out.printf("%s's membership unfrozen. Status: %s.\n", member.getName(), status);
        }
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.printf("%s's membership expired. Status: %s.\n", member.getName(), status);
    }
}

public class Main {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMem = new Membership(asha, new QuarterlyPlan());
        Membership raviMem = new Membership(ravi, new MonthlyPlan());

        ashaMem.checkIn();
        ashaMem.freeze();
        ashaMem.checkIn();

        raviMem.expire();
        raviMem.freeze();
    }
}
