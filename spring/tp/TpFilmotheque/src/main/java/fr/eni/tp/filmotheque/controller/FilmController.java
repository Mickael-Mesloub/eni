package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import org.springframework.stereotype.Component;

@Component
public class FilmController {
    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    public void afficherFilms() {
        this.filmService.consulterFilms().forEach(System.out::println);
    }

    public void afficherUnFilm(int id) {
        System.out.println(this.filmService.consulterFilmParId(id));
    }
}
