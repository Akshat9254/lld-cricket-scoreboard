public class Match {
    private Team battingTeam;
    private Team bowlingTeam;

    private Player onStrikeBatter;
    private Player nonStrikeBatter;
    private MatchStatus status;
    private int totalOvers;
    private int currentOver;
    private int legalBallsBowled;
    private Integer target;

    public Match(Team battingTeam, Team bowlingTeam, int totalOvers) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.totalOvers = totalOvers;
        this.legalBallsBowled = 0;
        this.currentOver = 0;
        this.status = MatchStatus.RUNNING;
        this.onStrikeBatter = battingTeam.getNextPlayer();
        this.nonStrikeBatter = battingTeam.getNextPlayer();
        this.target = null;
    }

    public Integer getTarget() {
        return target;
    }

    public void setCurrentOver(int currentOver) {
        this.currentOver = currentOver;
    }

    public void setLegalBallsBowled(int legalBallsBowled) {
        this.legalBallsBowled = legalBallsBowled;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public void incrementLegalBallsBowled() {
        this.legalBallsBowled++;
    }

    public int getCurrentOver() {
        return currentOver;
    }

    public void incrementCurrentOver() {
        this.currentOver++;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setOnStrikeBatter(Player onStrikeBatter) {
        this.onStrikeBatter = onStrikeBatter;
    }

    public void setNonStrikeBatter(Player nonStrikeBatter) {
        this.nonStrikeBatter = nonStrikeBatter;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public int getLegalBallsBowled() {
        return legalBallsBowled;
    }

    public Player getOnStrikeBatter() {
        return onStrikeBatter;
    }

    public Player getNonStrikeBatter() {
        return nonStrikeBatter;
    }
}
