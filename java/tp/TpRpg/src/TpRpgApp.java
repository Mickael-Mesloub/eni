import model.characters.Character;
import model.Game;
import model.Team;
import model.characters.heroes.*;
import model.characters.creatures.*;

import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {

        // Instances des combattants de la team Héros
        Character warrior = new Warrior("Warrior");
        Character mage = new Mage("Mage");
        Character tank = new Tank("Tank");

        // Instances des combattants de la team Créatures
        Character wolf = new Wolf("Wolf");
        Character vampire = new Vampire("Vampire");
        Character golem = new Golem("Golem");

        // Instance de l'équipe Héros
        Team playerTeam = new Team("Héros", new ArrayList<Character>(List.of(warrior, mage, tank)));

        // Instance de l'équipe Créatures
        Team enemyTeam = new Team("Créatures", new ArrayList<Character>(List.of(wolf, vampire, golem)));

        // Instance de la partie
        Game game = new Game(playerTeam, enemyTeam);

        // Lancer une partie
        game.play();
    }
}