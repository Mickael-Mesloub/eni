package fr.eni.tp.chifoumi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/chifoumi")
public class GameController {

    @GetMapping("/jouer")
    public String jouer(
            @RequestParam(value = "value") String value
    ) {
        List<String> values = new ArrayList<>();
        values.add("pierre");
        values.add("feuille");
        values.add("ciseaux");

        String randomValue = values.get(new Random().nextInt(values.size()));
        System.out.println("RANDOM VALUE : " + randomValue);
        System.out.println("VALUE ENVOYÉE : " + value);

        if (randomValue.equals("pierre") && value.equals("pierre")
                || randomValue.equals("feuille") && value.equals("feuille")
                || randomValue.equals("ciseaux") && value.equals("ciseaux")) {
            System.out.println("**********\n ÉGALITÉ ! \n**********");
            return getDraw();
        }

        if (randomValue.equals("pierre") && value.equals("ciseaux")
        || randomValue.equals("feuille") && value.equals("pierre")
        || randomValue.equals("ciseaux") && value.equals("feuille")) {
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

}
