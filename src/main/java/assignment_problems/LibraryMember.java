import java.io.Serializable;
public class LibraryMember implements Serializable {
    private static final long serialVersionUID = 1L;
    private String membershipId;
    private String name;
    private boolean premiumMember;
    @SuppressWarnings("unused")
    private int securityAnswerHash;
    public LibraryMember() {
        this.membershipId = null;
        this.name = "";
        this.premiumMember = false;
        this.securityAnswerHash = 0;
    }
    public String getMembershipId() {
        return this.membershipId;
    }
    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isPremiumMember() {
        return this.premiumMember;
    }
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            int hash = 7;
            for (int i = 0; i < answer.length(); i++) {
                hash = 31 * hash + answer.charAt(i);
            }
            this.securityAnswerHash = hash;
        }
    }
    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("Membership ID: " + m.getMembershipId()); 
        m.setMembershipId("FAKE-0000");
        System.out.println("After Re-set: " + m.getMembershipId());  
        System.out.println("Is Premium: " + m.isPremiumMember());    
        m.setSecurityAnswer("BlueMountain");
    }
}
