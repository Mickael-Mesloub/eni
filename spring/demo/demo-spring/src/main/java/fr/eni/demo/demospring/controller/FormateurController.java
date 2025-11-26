package fr.eni.demo.demospring.controller;

import fr.eni.demo.demospring.bll.FormateurService;
import fr.eni.demo.demospring.bo.Formateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormateurController {
    private FormateurService formateurService;

    // Pour récupérer mon formateurService,
    // on va faire une injection de dépendance via le constructeur
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @GetMapping("/formateurs")
    public String afficherFormateurs(){
        List<Formateur> formateurs = formateurService.getFormateurs();
        for(Formateur formateur : formateurs){
            System.out.println(formateur);
        }

        return "view-formateurs";
    }
}
