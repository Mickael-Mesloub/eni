package fr.eni.demo.demospring.controller;

import fr.eni.demo.demospring.bll.CoursService;
import fr.eni.demo.demospring.bll.FormateurService;
import fr.eni.demo.demospring.bo.Cours;
import fr.eni.demo.demospring.bo.Formateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@SessionAttributes({"listeCoursSession"})
@Controller
@RequestMapping("/formateurs")
public class FormateurController {
    private FormateurService formateurService;
    private CoursService coursService;

    // Pour récupérer mon formateurService,
    // on va faire une injection de dépendance via le constructeur
    public FormateurController(FormateurService formateurService,  CoursService coursService) {

        this.formateurService = formateurService;
        this.coursService = coursService;
    }

    @GetMapping
    public String afficherFormateurs(Model model) {
        List<Formateur> formateurs = formateurService.getFormateurs();
        for (Formateur formateur : formateurs) {
            System.out.println(formateur);
        }

        model.addAttribute("formateurs", formateurs);

        return "view-formateurs";
    }

    @PostMapping("/detail")
    public String majFormateur(
            @RequestParam(name = "nom") String nomFormateur,
            @RequestParam(name = "prenom") String prenomFormateur,
            @RequestParam(name = "email") String emailFormateur) {

        System.out.println("POST FORM");
        System.out.println("Email: " + emailFormateur);
        System.out.println("Nom: " + nomFormateur);
        System.out.println("Prenom: " + prenomFormateur);

        // Redirection (code statut 302) vers /formateurs
        return "redirect:/formateurs";
    }

    @GetMapping("/detail")
    public String detailFormateur(@RequestParam(name = "email") String emailFormateur, Model model) {
        System.out.println("Email : " + emailFormateur);

        Formateur formateurTrouve = formateurService.findByEmail(emailFormateur);
        model.addAttribute("formateur", formateurTrouve);

        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        model.addAttribute("randomBoolean", randomBoolean);

        return "view-formateur-detail";
    }

    @PostMapping("/cours")
    public String ajouterCours(@RequestParam ("email") String emailFormateur,
    @RequestParam("idCours") long idCours){
        System.out.println("ajouterCours - email formateur : " + emailFormateur);
        System.out.println("ajouterCours - id cours : " + idCours);

        formateurService.updateCoursFormateur(emailFormateur, idCours);

        return "redirect:/formateurs/detail?email=" + emailFormateur;
    }

    @ModelAttribute("listeCoursSession")
    public List<Cours> chargerCoursEnSession(){
        System.out.println("Appel à la méthode chargerCoursEnSession");
        return this.coursService.getCours();
    }
}
