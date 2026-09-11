public class TypingSpeedAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }
        int totalChars = original.length();
        int matchedCount = 0;
        int firstMismatchPos = -1;
        char origMismatchChar = ' ';
        char typedMismatchChar = ' ';
        for (int i = 0; i < totalChars; i++) {
            char origChar = original.charAt(i);
            char typedChar = typed.charAt(i);
            if (origChar == typedChar) {
                matchedCount++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
                origMismatchChar = origChar;
                typedMismatchChar = typedChar;
            }
        }
        double accuracy = totalChars == 0 ? 100.0 : ((double) matchedCount / totalChars) * 100.0;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Matched: %d/%d | Accuracy: %.2f%% | ", matchedCount, totalChars, accuracy));
        if (firstMismatchPos == -1) {
            sb.append("No Mismatches");
        } else {
            sb.append(String.format("First Mismatch at position %d ('%c' vs '%c')",
                    firstMismatchPos, origMismatchChar, typedMismatchChar));
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
