import java.io.Serializable;
public class MovieBookingProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private boolean confirmed;
    @SuppressWarnings("unused")
    private String otp; 
    public MovieBookingProfile() {
        this.name = "";
        this.confirmed = false;
        this.otp = null;
    }
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isConfirmed() {
        return this.confirmed;
    }
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    public static void main(String[] args) {
        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        System.out.println("Name: " + p.getName()); 
        p.setConfirmed(true);
        System.out.println("isConfirmed: " + p.isConfirmed()); 
        p.setOtp("4471"); 
    }
}
