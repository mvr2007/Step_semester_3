public class ExamHallSeatChecker {
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length <= 1) {
            System.out.println("No Duplicate Seats Found");
            return;
        }
        boolean foundAnyDuplicate = false;
        boolean[] reported = new boolean[seatNumbers.length];
        for (int i = 0; i < seatNumbers.length; i++) {
            if (reported[i]) {
                continue;
            }
            boolean isDuplicate = false;
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    isDuplicate = true;
                    reported[j] = true; 
                }
            }
            if (isDuplicate) {
                System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                foundAnyDuplicate = true;
            }
        }
        if (!foundAnyDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }
    public static void main(String[] args) {
        int[] hall1 = {101, 102, 103, 102, 105};
        int[] hall2 = {101, 102, 103, 104, 105};
        System.out.print("Hall 1 Output: ");
        checkDuplicateSeats(hall1);
        System.out.print("Hall 2 Output: ");
        checkDuplicateSeats(hall2);
    }
}
