import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {

        // Instances des combattants de la team Gentil
        Battler me = new Battler("Gentil-1");
        Battler gentil = new Battler("Gentil-2");

        // Instances des combattants de la team Méchant
        Battler opponent = new Battler("Méchant-1");
        Battler mechant = new Battler("Méchant-2");

        // Instance de l'équipe Gentil
        Team playerTeam = new Team("Gentil", new ArrayList<Battler>(List.of(me, gentil)));

        // Instance de l'équipe Méchant
        Team enemyTeam = new Team("Méchant", new ArrayList<Battler>(List.of(opponent, mechant)));

        // Instance de la partie
        Game game = new Game(playerTeam, enemyTeam);

        // Lancer une partie
        game.play();
    }
}