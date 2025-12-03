package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 1. Ajout d'un film :
 * TODO 1.1 : uniquement avec un titre ✅
 * TODO 1.2 : puis une fois que ça fonctionne, ajouter année, durée, synopsis ✅
 * TODO 1.3 : mettre en place la validation de données
 * TODO 1.4 : gérer le genre d'un film -> associer un genre à un film
 *  + gérer la liste des genes au niveau application (au lieu de le faire en session) ${@myBean.doSomething()}
 * TODO 1.5 : gérer le réalisateur
 * TODO 1.6 : gérer les acteurs
 */

@SessionAttributes({"listeGenresEnSession"})
@Controller
@RequestMapping("/films")
public class FilmController {
    FilmService filmService;
    List<Genre> listeGenres;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
        this.listeGenres = new ArrayList<>();
        listeGenres.addAll(this.filmService.consulterGenres());
    }

    @GetMapping
    public String getFilms(Model model) {
        List<Film> films = filmService.consulterFilms();
        model.addAttribute("films", films);
        return "view-films";
    }

    @GetMapping("/detail")
    public String getFilmDetails(@RequestParam long id, Model model) {
        Film filmEntity = filmService.consulterFilmParId(id);

        model.addAttribute("film", filmEntity);

        return "view-film-detail";
    }

    @GetMapping("/creer")
    public String viewCreerFilm(Model model) {

        if (!model.containsAttribute("film")) {
            model.addAttribute("film", new FilmDTO());
        }

        return "view-creer-film";
    }

    @PostMapping("/creer")
    public String creerFilm(
            @Valid @ModelAttribute("film") FilmDTO filmDTO,
            BindingResult resultat,
            RedirectAttributes redirectAttr
    ) {

        // TODO data validation
        if(resultat.hasErrors()) {
            System.out.println("ERREURS DÉTECTÉES :");
            redirectAttr.addFlashAttribute( "org.springframework.validation.BindingResult.film", resultat);
            redirectAttr.addFlashAttribute("film", filmDTO);
            resultat.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "redirect:/films/creer";
        }

        Film newFilm = new Film();

        BeanUtils.copyProperties(filmDTO, newFilm);
        filmService.creerFilm(newFilm);

        return "redirect:/films";
    }

    @ModelAttribute("listeGenresEnSession")
    public List<Genre> chargerGenresEnSession() {
        System.out.println("Appel à la méthode chargerGenresEnSession");
        return this.listeGenres;
    }
}
