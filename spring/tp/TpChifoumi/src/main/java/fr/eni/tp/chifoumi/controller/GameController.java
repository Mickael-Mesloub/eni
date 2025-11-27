package fr.eni.tp.chifoumi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/chifoumi")
public class GameController {
    private List<String> values = new ArrayList<>();
    private GameResult result;

    public GameController() {
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
    public String play(String playerChoice) {
        String serverChoice = values.get(new Random().nextInt(values.size()));
        gameRules(playerChoice, serverChoice);
        System.out.println("Choix serveur : " + serverChoice);
        System.out.println("Choix joueur : " + playerChoice);
        if (result.equals(GameResult.DRAW)) {
            System.out.println("**********\n ÉGALITÉ ! \n**********");
            return getDraw();
        } else if (result.equals(GameResult.LOST)) {
            System.out.println("**********\n  PERDU... \n**********");
            return getLost();
        } else {
            System.out.println("**********\n GAGNÉ !!! \n**********");
            return getWon();
        }
    }

    /**
     * Jouer la partie en GET
     * @param playerChoice Le choix du joueur
     * @return La vue selon le résultat
     */
    @GetMapping("/jouer")
    public String jouer(
            @RequestParam(name = "choice") String playerChoice
    ) {
       return play(playerChoice);
    }

    /**
     * Jouer la partie en POST via formulaire
     * @param playerChoice Le choix du joueur
     * @return La vue selon le résultat
     */
    @PostMapping("/jouer")
    public String postJouer(@RequestParam(name = "choice") String playerChoice) {
        return play(playerChoice);
    }

    @GetMapping
    public String getChifoumi() {
        return "view-chifoumi";
    }

    @GetMapping("/jouer/won")
    public String getWon() {
        return "view-won";
    }

    @GetMapping("/jouer/lost")
    public String getLost() {
        return "view-lost";
    }

    @GetMapping("/jouer/draw")
    public String getDraw() {
        return "view-draw";
    }
}
