import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final static int ballsPerOver = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter number of players for each team");
        int numPlayers = Integer.parseInt(br.readLine());
        System.out.println("Please enter total number of overs for each team");
        int totalOvers = Integer.parseInt(br.readLine());

        Team team1 = new Team("Mumbai Indians", numPlayers);
        Team team2 = new Team("Chennai Super Kings", numPlayers);
        Match match = new Match(team1, team2, totalOvers);

        MatchHandler matchHandler = new MatchHandler(match);


        while(match.getStatus() != MatchStatus.ENDED) {
            System.out.println("Enter runs / wide / no / W");
            String input = br.readLine();
            if(isInteger(input)) {
                int runs = Integer.parseInt(input);
                if(runs == 4) {
                    matchHandler.handleFours();
                } else if(runs == 6) {
                    matchHandler.handleSixes();
                } else {
                    matchHandler.handleRuns(runs);
                }
            } else if(input.equals("wide") || input.equals("no")) {
                matchHandler.handleExtras();
            } else if(input.equals("W")) {
                matchHandler.handleWicketFall();
            } else {
                System.out.println("Invalid input");
                continue;
            }

            boolean isExtra = input.equals("wide") || input.equals("no");

            if(!isExtra) {
                 matchHandler.incrementBalls();
            }

            if(match.getLegalBallsBowled() % ballsPerOver == 0 && !isExtra) {
                matchHandler.incrementOver();
                matchHandler.changeStrike();
            }

            boolean isInningEnded = match.getLegalBallsBowled() == ballsPerOver * totalOvers ||
                    match.getBattingTeam().getWickets() == numPlayers - 1 ||
                    (match.getTarget() != null && match.getBattingTeam().getTotalRuns() > match.getTarget());

            if(isInningEnded) {
                matchHandler.handleInningsOver();
            } else {
                matchHandler.printScorecard();
            }
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
