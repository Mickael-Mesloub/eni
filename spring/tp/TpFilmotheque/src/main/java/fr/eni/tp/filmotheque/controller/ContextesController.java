package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.contexte.ContexteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/contextes")
@Controller
public class ContextesController {
    ContexteService  contexteService;
    public ContextesController(ContexteService contexteService) {
        this.contexteService = contexteService;
    }

    @GetMapping
    public String getContextes(Model model){
        model.addAllAttributes(contexteService.getListeMembres());
        return "view-contextes";
    }
}
