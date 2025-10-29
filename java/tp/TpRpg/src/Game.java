import java.util.ArrayList;
import java.util.List;

public class Game {
    public Team playerTeam;
    public Team enemyTeam;

    // TODO: implémenter la logique
    public void play(){
        System.out.println("Play!");
    }

    // TODO: implémenter la logique
    public Team getWinnerTeam(){
        Team winner = (playerTeam.isLooser()) ? enemyTeam : playerTeam;

        System.out.println(String.format("\uD83C\uDFC6 %s won!",winner.name));

        return winner;

    }
}
