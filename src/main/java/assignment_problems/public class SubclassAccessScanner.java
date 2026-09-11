public class SubclassAccessScanner {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }
        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";
            case "protected":
                switch (accessorContext) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                        return "ALLOWED";
                    case "DIFFERENT_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                        return "DENIED";
                    default:
                        return "DENIED";
                }
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
    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) {
            return "None Denied";
        }
        for (int i = 0; i < attempts.length; i++) {
            String[] attempt = attempts[i];
            if (attempt != null && attempt.length >= 2) {
                String modifier = attempt[0];
                String context = attempt[1];
                String verdict = classifyAccess(modifier, context);

                if ("DENIED".equals(verdict)) {
                    return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
                }
            }
        }
        return "None Denied";
    }
    public static void main(String[] args) {
        String[][] batch1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(batch1));
        String[][] batch2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(batch2));
    }
}
