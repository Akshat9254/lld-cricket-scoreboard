import java.util.List;
public class MatchHandler {
    private final Match match;

    public MatchHandler(Match match) {
        this.match = match;
    }

    public void handleRuns(int runs) {
        Player onStrikeBatter = match.getOnStrikeBatter();
        onStrikeBatter.incrementRuns(runs);

        Team battingTeam = match.getBattingTeam();
        battingTeam.incrementRuns(runs);

        if(runs % 2 != 0) changeStrike();
    }

    public void handleFours() {
        Player onStrikeBatter = match.getOnStrikeBatter();
        onStrikeBatter.incrementFours();
        handleRuns(4);
    }

    public void handleSixes() {
        Player onStrikeBatter = match.getOnStrikeBatter();
        onStrikeBatter.incrementSixes();
        handleRuns(6);
    }

    public void handleWicketFall() {
        Team battingTeam = match.getBattingTeam();
        battingTeam.incrementWickets();

        Player nextPlayer = battingTeam.getNextPlayer();
        match.setOnStrikeBatter(nextPlayer);
    }

    public void handleExtras() {
        Team battingTeam = match.getBattingTeam();
        battingTeam.incrementExtras();
    }

    public void changeStrike() {
        Player onStrikeBatter = match.getOnStrikeBatter();
        Player nonStrikeBatter = match.getNonStrikeBatter();
        match.setOnStrikeBatter(nonStrikeBatter);
        match.setNonStrikeBatter(onStrikeBatter);
    }

    public void incrementBalls() {
        match.incrementLegalBallsBowled();
    }

    public void incrementOver() {
        match.incrementCurrentOver();
    }

    public void endInnings() {
        Team battingTeam = match.getBattingTeam();
        Team bowlingTeam = match.getBowlingTeam();
        match.setBattingTeam(bowlingTeam);
        match.setBowlingTeam(battingTeam);
    }

    public void printScorecard() {
        Team battingTeam = match.getBattingTeam();
        List<Player> players = battingTeam.getPlayers();
        System.out.format("%10s%10s%10s%10s", "Player", "Runs", "4s", "6s");
        System.out.println();
        for(Player player: players) {
            boolean isOnStrikePlayer = match.getOnStrikeBatter().getName().equals(player.getName());
            System.out.format("%10s%10s%10s%10s", player.getName() + (isOnStrikePlayer ? "*" : ""),
                    player.getRuns(),
                    player.getFours(),
                    player.getSixes());
            System.out.println();
        }

        System.out.println("Runs: " + battingTeam.getTotalRuns() + " / " + battingTeam.getWickets());
        int currentOverBall = match.getLegalBallsBowled() % 6;
        System.out.println("Overs: " + match.getCurrentOver() + "." + currentOverBall + " / " + match.getTotalOvers());
    }

    public void handleInningsOver() {
        boolean isFirstInningEnd = match.getTarget() == null;
        if(isFirstInningEnd) {
            System.out.println("Change of Innings");
            System.out.println("Target for " +
                    match.getBowlingTeam().getName() + " is " +
                    (match.getBattingTeam().getTotalRuns()+1));
            Team battingTeam = match.getBattingTeam();
            Team bowlingTeam = match.getBowlingTeam();
            match.setBattingTeam(bowlingTeam);
            match.setBowlingTeam(battingTeam);
            match.setOnStrikeBatter(bowlingTeam.getNextPlayer());
            match.setNonStrikeBatter(bowlingTeam.getNextPlayer());
            match.setCurrentOver(0);
            match.setLegalBallsBowled(0);
            match.setTarget(battingTeam.getTotalRuns());
        } else {
            //match result
            Team battingTeam = match.getBattingTeam();
            Team bowlingTeam = match.getBowlingTeam();
            int totalRuns = battingTeam.getTotalRuns();
            if(totalRuns > match.getTarget()) {
                System.out.println(battingTeam.getName() + " won");
            } else if(totalRuns == match.getTarget()) {
                System.out.println("Its a Tie");
            } else {
                System.out.println(bowlingTeam.getName() + " won");
            }

            match.setStatus(MatchStatus.ENDED);
        }
    }
}
