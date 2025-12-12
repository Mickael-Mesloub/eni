package fr.eni.tp.filmotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/", "/accueil"})
    public String accueil() {
        return "view-accueil";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
