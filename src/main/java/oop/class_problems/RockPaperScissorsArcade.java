import java.util.Random;
public class RockPaperScissorsArcade {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }
    public static void main(String[] args) {
        int rounds = 5;
        Random random = new Random();
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        String[][] history = new String[rounds][4];
        int wins = 0, losses = 0, draws = 0;
        for (int i = 0; i < rounds; i++) {
            String playerMove = playerMoves[i];
            String computerMove = MOVES[random.nextInt(MOVES.length)];
            String result = playRound(playerMove, computerMove);
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
            history[i][0] = String.valueOf(i + 1);
            history[i][1] = playerMove;
            history[i][2] = computerMove;
            history[i][3] = result;
        }
        System.out.println("==================== GAME SUMMARY ====================");
        System.out.printf("%-8s | %-12s | %-14s | %-14s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("------------------------------------------------------");
        for (String[] row : history) {
            System.out.printf("%-8s | %-12s | %-14s | %-14s%n", row[0], row[1], row[2], row[3]);
        }
        System.out.println("------------------------------------------------------");
        double winPercentage = ((double) wins / rounds) * 100;
        System.out.printf("Total Wins: %d | Losses: %d | Draws: %d | Win %%: %.1f%%%n",
                wins, losses, draws, winPercentage);
    }
}
