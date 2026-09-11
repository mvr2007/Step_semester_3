public class PalindromeChecker {
    public static boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        return checkRecursiveHelper(text.toLowerCase(), 0, text.length() - 1);
    }
    private static boolean checkRecursiveHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return checkRecursiveHelper(s, left + 1, right - 1);
    }
    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] original = text.toLowerCase().toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }
    public static void verifyText(String text) {
        boolean r1 = isPalindromeIterative(text);
        boolean r2 = isPalindromeRecursive(text);
        boolean r3 = isPalindromeArrayReversal(text);
        System.out.printf("Input: \"%s\"%n", text);
        System.out.printf("  Iterative: %s | Recursive: %s | Array Reversal: %s%n",
                r1 ? "Palindrome" : "Not Palindrome",
                r2 ? "Palindrome" : "Not Palindrome",
                r3 ? "Palindrome" : "Not Palindrome");
        System.out.println("  All Approaches Agree: " + (r1 == r2 && r2 == r3));
        System.out.println();
    }
    public static void main(String[] args) {
        verifyText("madam");
        verifyText("hello");
        verifyText("Racecar");
    }
}
