public class TrafficSignalStreakAnalyzer {
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Log is empty.");
            return;
        }
        char longestColor = signalLog.charAt(0);
        int maxStreak = 1;
        char currentColor = signalLog.charAt(0);
        int currentStreak = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentColor) {
                currentStreak++;
            } else {
                currentColor = c;
                currentStreak = 1;
            }
            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
                longestColor = currentColor;
            }
        }
        System.out.println("Longest Streak: '" + longestColor + "' repeated " + maxStreak + " times");
    }
    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
