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

    // TODO: implémenter la logique
    public void play(){

    }

    // TODO: implémenter la logique
    // pour chaque team, vérifier si isLooser(); Si oui, alors l'autre team gagne. Tant que les 2 sont false, winner vaut null
    public Team getWinnerTeam(){
        Team winner = (playerTeam.isLooser()) ? enemyTeam : playerTeam;

        System.out.println(String.format("\uD83C\uDFC6 %s won!",winner.name));

        return winner;

    }
}
