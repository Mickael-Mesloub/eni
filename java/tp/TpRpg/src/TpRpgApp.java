import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {

        // Instances des combattants de la team Gentil
        Battler me = new Battler("Gentil-1", 50, 50, 100);
        Battler gentil = new Battler("Gentil-2", 500, 30,500);

        // Instances des combattants de la team Méchant
        Battler opponent = new Battler("Méchant-1", 60, 50, 200);
        Battler mechant = new Battler("Méchant-2", 88, 50, 205);

        // Instance de l'équipe Gentil
        Team playerTeam = new Team("Gentil Team", new ArrayList<Battler>(List.of(me, gentil)));

        // Instance de l'équipe Méchant
        Team enemyTeam = new Team("Méchant Team", new ArrayList<Battler>(List.of(opponent, mechant)));

        // Instance de la partie
        Game game = new Game(playerTeam, enemyTeam);

        // Lancer une partie
        game.play();
    }
}