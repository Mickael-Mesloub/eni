import model.Character;
import model.Game;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {

        // Instances des combattants de la team Gentil
        Character me = new Character("Gentil-1");
        Character gentil = new Character("Gentil-2");

        // Instances des combattants de la team Méchant
        Character opponent = new Character("Méchant-1");
        Character mechant = new Character("Méchant-2");

        // Instance de l'équipe Gentil
        Team playerTeam = new Team("Gentil", new ArrayList<Character>(List.of(me, gentil)));

        // Instance de l'équipe Méchant
        Team enemyTeam = new Team("Méchant", new ArrayList<Character>(List.of(opponent, mechant)));

        // Instance de la partie
        Game game = new Game(playerTeam, enemyTeam);

        // Lancer une partie
        game.play();
    }
}