package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"listeGenresEnSession"})
@Controller
@RequestMapping("/films")
public class FilmController {
    FilmService filmService;
    FilmService filmServiceImpl;

    public FilmController(FilmService filmService,  FilmService filmServiceImpl) {
        this.filmService = filmService;
        this.filmServiceImpl = filmServiceImpl;
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
            Model model,
            RedirectAttributes redirectAttr
    ) {
        // Data validation
        if (resultat.hasErrors()) {
            // Les redirectAttr servent à renvoyer les erreurs (resultat) et les précédentes saisies utilisateur (filmDTO)
            // dans la nouvelle requête lors du redirect dans le return.
            redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.film", resultat);
            redirectAttr.addFlashAttribute("film", filmDTO);
            return "redirect:/films/creer";
        }

        Film newFilm = new Film();
        newFilm.setGenre(filmService.consulterGenreParId(filmDTO.getIdGenre()));
        newFilm.setRealisateur(filmService.consulterParticipantParId(filmDTO.getIdRealisateur()));

        filmDTO.getIdsActeurs().forEach(id -> newFilm.getActeurs().add(filmService.consulterParticipantParId(id)));

        // Récupère les propriétés identiques entre l'objet source (filmDto) et l'objet cible (newFilm)
        // et il les copie dans l'objet cible.
        BeanUtils.copyProperties(filmDTO, newFilm);
        filmService.creerFilm(newFilm);

        return "redirect:/films";
    }
}
