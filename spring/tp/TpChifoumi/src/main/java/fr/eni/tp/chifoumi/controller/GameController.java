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

    // TODO Simplify + refactor
    @GetMapping("/jouer")
    public String jouer(
            @RequestParam(value = "value") String value
    ) {
        List<String> values = new ArrayList<>();
        values.add("pierre");
        values.add("feuille");
        values.add("ciseaux");

        String serverChoice = values.get(new Random().nextInt(values.size()));
        System.out.println("Choix serveur : " + serverChoice);
        System.out.println("Choix joueur : " + value);

        if (serverChoice.equals("pierre") && value.equals("pierre")
                || serverChoice.equals("feuille") && value.equals("feuille")
                || serverChoice.equals("ciseaux") && value.equals("ciseaux")) {
            System.out.println("**********\n ÉGALITÉ ! \n**********");
            return getDraw();
        }

        if (serverChoice.equals("pierre") && value.equals("ciseaux")
        || serverChoice.equals("feuille") && value.equals("pierre")
        || serverChoice.equals("ciseaux") && value.equals("feuille")) {
            System.out.println("**********\n  PERDU... \n**********");
          return getLost();
        }
        else {
            System.out.println("**********\n GAGNÉ !!! \n**********");
           return getWon();
        }
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

    // TODO Simplify + refactor
    @PostMapping("/jouer")
    public String postJouer(@RequestParam(name = "choice") String playerChoice) {
        List<String> values = new ArrayList<>();
        values.add("pierre");
        values.add("feuille");
        values.add("ciseaux");

        String serverChoice = values.get(new Random().nextInt(values.size()));
        System.out.println("Choix serveur : " + serverChoice);
        System.out.println("Choix joueur : " + playerChoice);

        if (serverChoice.equals("pierre") && playerChoice.equals("pierre")
                || serverChoice.equals("feuille") && playerChoice.equals("feuille")
                || serverChoice.equals("ciseaux") && playerChoice.equals("ciseaux")) {
            System.out.println("**********\n ÉGALITÉ ! \n**********");
            return getDraw();
        }

        if (serverChoice.equals("pierre") && playerChoice.equals("ciseaux")
                || serverChoice.equals("feuille") && playerChoice.equals("pierre")
                || serverChoice.equals("ciseaux") && playerChoice.equals("feuille")) {
            System.out.println("**********\n  PERDU... \n**********");
            return getLost();
        }
        else {
            System.out.println("**********\n GAGNÉ !!! \n**********");
            return getWon();
        }
    }

}
