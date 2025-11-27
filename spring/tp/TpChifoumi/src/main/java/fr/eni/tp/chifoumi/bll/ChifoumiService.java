package fr.eni.tp.chifoumi.bll;

import fr.eni.tp.chifoumi.bo.Partie;
import fr.eni.tp.chifoumi.controller.GameResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ChifoumiService {
    private List<String> values = new ArrayList<>();
    private GameResult result;
    private Partie partie;

    public ChifoumiService() {
        values.add("pierre");
        values.add("feuille");
        values.add("ciseaux");
    }

    /**
     * Met à jour l'attribut result
     * @param playerChoice Le choix du joueur
     */
    public void gameRules(String playerChoice, String serverChoice) {
        if (serverChoice.equals(playerChoice)) {
            result = GameResult.DRAW;
        } else if (serverChoice.equals("pierre") && playerChoice.equals("ciseaux")
                || serverChoice.equals("feuille") && playerChoice.equals("pierre")
                || serverChoice.equals("ciseaux") && playerChoice.equals("feuille")) {
            result = GameResult.LOST;
        } else {
            result = GameResult.WON;
        }
    }

    /**
     * La méthode générique pour jouer une partie
     * @param playerChoice Le choix du joueur
     * @return La vue à render
     */
    public Partie play(String playerChoice) {
        String serverChoice = values.get(new Random().nextInt(values.size()));
        gameRules(playerChoice, serverChoice);
        System.out.println("Choix serveur : " + serverChoice);
        System.out.println("Choix joueur : " + playerChoice);

        if (serverChoice.equals(playerChoice)) {
            result = GameResult.DRAW;
            System.out.println("**********\n ÉGALITÉ ! \n**********");
        } else if (serverChoice.equals("pierre") && playerChoice.equals("ciseaux")
                || serverChoice.equals("feuille") && playerChoice.equals("pierre")
                || serverChoice.equals("ciseaux") && playerChoice.equals("feuille")) {
            result = GameResult.LOST;
            System.out.println("**********\n  PERDU... \n**********");
        } else {
            result = GameResult.WON;
            System.out.println("**********\n GAGNÉ !!! \n**********");
        }

        return new Partie(playerChoice, serverChoice, result);
    }
}
