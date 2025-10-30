import java.util.ArrayList;
import java.util.List;

public class Game {
    public Team playerTeam;
    public Team enemyTeam;
    ArrayList<Team> teams;

    public Game(Team playerTeam, Team enemyTeam) {
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        teams = new ArrayList<Team>(List.of(playerTeam, enemyTeam));
    }

    // TODO: fix enemy team attacking after battler is dead
    public void play(){
       if(!playerTeam.isLoser() || !enemyTeam.isLoser()) {
           playerTeam.battlers.getFirst().attack(enemyTeam.battlers.getFirst());
           enemyTeam.battlers.getFirst().attack(playerTeam.battlers.getFirst());
       }
    }

    // TODO: refactor ?
    public Team getWinnerTeam() {
        Team winner = null;

        if(playerTeam.isLoser()) {
            winner = enemyTeam;
            System.out.println(String.format("🏆 %s won!", enemyTeam.name));
        } else if(enemyTeam.isLoser()) {
            winner = playerTeam;
            System.out.println(String.format("🏆 %s won!", playerTeam.name));
        }

        return winner;
    }

}
