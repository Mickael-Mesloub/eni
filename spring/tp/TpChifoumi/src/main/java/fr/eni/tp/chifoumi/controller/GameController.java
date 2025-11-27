package fr.eni.tp.chifoumi.controller;

import fr.eni.tp.chifoumi.bll.ChifoumiService;
import fr.eni.tp.chifoumi.bo.Partie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/chifoumi")
public class GameController {
    ChifoumiService  chifoumiService;

    public GameController(ChifoumiService chifoumiService) {
        this.chifoumiService = chifoumiService;
    }

    @GetMapping
    public String getChifoumi() {
        return "view-chifoumi";
    }

    /**
     * Jouer la partie en GET
     * @param playerChoice Le choix du joueur
     * @return La vue avec le model injecté pour afficher les informations de la partie
     */
    @GetMapping("/jouer")
    public String jouer(
            @RequestParam(name = "choice") String playerChoice, Model model
    ) {
       Partie partie = chifoumiService.play(playerChoice);
       model.addAttribute("partie", partie);

       return "view-result";
    }

    /**
     * Jouer la partie en POST via formulaire
     * @param playerChoice Le choix du joueur
     * @return La vue avec le model injecté pour afficher les informations de la partie
     */
    @PostMapping("/jouer")
    public String postJouer(@RequestParam(name = "choice") String playerChoice, Model model) {
        Partie partie = chifoumiService.play(playerChoice);
        model.addAttribute("partie", partie);

        return "view-result";
    }
}
