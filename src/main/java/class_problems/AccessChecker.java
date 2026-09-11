public class AccessChecker {
    private String seatNumber;
    String screenId;              
    protected double ticketPrice;
    public String movieTitle;
    public AccessChecker(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }
        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";
            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            default:
                return "DENIED";
        }
    }
    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) {
            return "Allowed: 0 | Denied: 0";
        }
        int allowed = 0;
        int denied = 0;
        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if ("ALLOWED".equals(result)) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));         
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); 
        String[][] batch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch)); 
    }
}
