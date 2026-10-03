public class TopperFinder {
    public static int[] findTopper(int[][] marks) {
        int bestRowIndex = 0;
        int maxTotal = -1;
        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRowIndex = i;
            }
        }
        return new int[]{bestRowIndex, maxTotal};
    }
    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };
        int[] result = findTopper(marks);
        System.out.println("Row Index: " + result[0] + ", Total: " + result[1]); 
    }
}
