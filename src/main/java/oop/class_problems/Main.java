import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\[");
            String name = parts[0].trim();
            String[] scores = parts[1].replace("]", "").split(",");

            int[] m = new int[scores.length];
            for (int i = 0; i < scores.length; i++) {
                m[i] = Integer.parseInt(scores[i].trim());
            }

            names.add(name);
            marksList.add(m);
        }

        int numStudents = names.size();
        int numSubjects = 3;
        int[] studentTotals = new int[numStudents];
        double[] subjectSums = new double[numSubjects];

        int maxTotal = -1;
        String topper = "";

        StringBuilder totalsOutput = new StringBuilder("Totals ");

        for (int i = 0; i < numStudents; i++) {
            int total = 0;
            int[] m = marksList.get(i);
            for (int j = 0; j < numSubjects; j++) {
                total += m[j];
                subjectSums[j] += m[j];
            }
            studentTotals[i] = total;

            if (total > maxTotal) {
                maxTotal = total;
                topper = names.get(i);
            }

            totalsOutput.append(names.get(i)).append(" ").append(total);
            if (i < numStudents - 1) {
                totalsOutput.append(", ");
            }
        }

        System.out.println(totalsOutput.toString());

        System.out.printf("averages %.2f, %.2f, %.2f\n",
                subjectSums[0] / numStudents,
                subjectSums[1] / numStudents,
                subjectSums[2] / numStudents);

        System.out.println("topper " + topper + " (" + maxTotal + ")");
    }
}
