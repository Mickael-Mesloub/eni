package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        Film film = filmService.consulterFilmParId(id);
        model.addAttribute("film", film);

        return "view-film-detail";
    }

    @GetMapping("/creer")
    public String creer() {
        return "view-creer-film";
    }

    @PostMapping("/creer")
    public String creerFilm(@ModelAttribute("film") Film film) {
        filmService.creerFilm(film);

        return "redirect:/films";
    }

    @ModelAttribute("listeGenresEnSession")
    public List<Genre> chargerGenresEnSession(){
        System.out.println("Appel à la méthode chargerGenresEnSession");
        return this.listeGenres;
    }
}
