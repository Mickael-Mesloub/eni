import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {
        Battler me = new Battler("sUp3rK3v", 50, 20);
        Battler opponent = new Battler("n0obl@rd", 60, 6);

        Team playerTeam = new Team("Player Team", new ArrayList<Battler>(List.of(me)));
        Team enemyTeam = new Team("Enemy Team", new ArrayList<Battler>(List.of(opponent)));

        Game game = new Game(playerTeam, enemyTeam);

        game.play();

        // TODO: boucle while : tant qu'il n'y a pas de team gagnante, game.play();
        while (opponent.hp > 0) {
            me.attack(opponent);
        }
    }
}