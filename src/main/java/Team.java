import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Team {
    private final String name;
    private List<Player> players;
    private int nextPlayerIndex;
    private int totalRuns;
    private int wickets;
    private int extraRuns;
    private int numPlayers;


    public Team(String name, int numPlayers) {
        this.name = name;
        this.players = new ArrayList<>();
        for(int i = 1; i <= numPlayers; i++) {
            this.players.add(new Player("P " + i));
        }
        this.nextPlayerIndex = 0;
        this.numPlayers = numPlayers;
        this.totalRuns = 0;
        this.wickets = 0;
        this.extraRuns = 0;
    }

    public Player getNextPlayer() {
        if(nextPlayerIndex >= this.players.size()) return null;
        return this.players.get(nextPlayerIndex++);
    }

    public void incrementRuns(int runs) {
        this.totalRuns += runs;
    }

    public void incrementWickets() {
        this.wickets++;
    }

    public void incrementExtras() {
        incrementExtras(1);
    }

    public void incrementExtras(int extras) {
        this.extraRuns += extras;
        this.totalRuns += extras;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWickets() {
        return wickets;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
