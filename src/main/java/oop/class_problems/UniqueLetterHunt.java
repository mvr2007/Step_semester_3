import java.util.LinkedHashMap;
import java.util.Map;
public class UniqueLetterHunt {
    public static Character findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }
    public static void testWord(String input) {
        Character result = findFirstNonRepeatingChar(input);
        if (result != null) {
            System.out.printf("Input: \"%s\" -> First Non-Repeating Character: '%c'%n", input, result);
        } else {
            System.out.printf("Input: \"%s\" -> No Non-Repeating Character Found%n", input);
        }
    }
    public static void main(String[] args) {
        testWord("swiss");
        testWord("aabbcc");
        testWord("racecars");
        testWord("stream");
    }
}
