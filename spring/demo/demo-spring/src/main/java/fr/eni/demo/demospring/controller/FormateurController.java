package fr.eni.demo.demospring.controller;

import fr.eni.demo.demospring.bll.FormateurService;
import fr.eni.demo.demospring.bo.Formateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/formateurs")
public class FormateurController {
    private FormateurService formateurService;

    // Pour récupérer mon formateurService,
    // on va faire une injection de dépendance via le constructeur
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @GetMapping
    public String afficherFormateurs() {
        List<Formateur> formateurs = formateurService.getFormateurs();
        for (Formateur formateur : formateurs) {
            System.out.println(formateur);
        }

        return "view-formateurs";
    }

    @GetMapping("/detail")
    public String detailFormateur(@RequestParam(name = "email") String emailFormateur, Model model) {
        System.out.println("Email : " + emailFormateur);

        Formateur formateurTrouve = formateurService.getFormateurByEmail(emailFormateur);
        model.addAttribute("formateur", formateurTrouve);

        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        model.addAttribute("randomBoolean", randomBoolean);

        return "view-formateur-detail";
    }

    @PostMapping("/detail")
    public String majFormateur(
            @RequestParam(name = "nom") String nomFormateur,
            @RequestParam(name = "prenom") String prenomFormateur,
            @RequestParam(name = "email") String emailFormateur) {

//        System.out.println("POST FORM \n Email : " + emailFormateur + "\n Nom : " + nomFormateur + "\n Prénom : " + prenomFormateur);
        System.out.println("Email: " + emailFormateur);
        System.out.println("Nom: " + nomFormateur);
        System.out.println("Prenom: " + prenomFormateur);

        return "view-formateurs";
    }
}
