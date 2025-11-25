package fr.eni.demospring.controller;

import fr.eni.demospring.bll.FormateurService;
import fr.eni.demospring.bo.Formateur;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormateurController {
    private FormateurService formateurService;

    // Pour récupérer mon formateurService,
    // on va faire une injection de dépendance via le constructeur
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    public void  afficherFormateurs(){
        List<Formateur> formateurs = formateurService.getFormateurs();
        for(Formateur formateur : formateurs){
            System.out.println(formateur);
        }
    }
}
