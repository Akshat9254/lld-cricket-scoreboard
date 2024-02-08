public class Player {
    private final String name;
    private int runs;
    private int fours;
    private int sixes;
    private int ballsFaced;

    public Player(String name) {
        this.name = name;
        this.runs = 0;
        this.fours = 0;
        this.sixes = 0;
        this.ballsFaced = 0;
    }

    public String getName() {
        return name;
    }

    public int getRuns() {
        return runs;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void incrementRuns(int runs) {
        this.runs += runs;
    }

    public void incrementFours() {
        this.fours++;
    }

    public void incrementSixes() {
        this.sixes++;
    }

    public void incrementBallsFaced() {
        this.ballsFaced++;
    }
}
