package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bll.ParticipantsService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.controller.dto.FilmDTO;
import fr.eni.tp.filmotheque.controller.dto.GenreDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping()
public class FilmController {
    private FilmService filmService;
    private FilmService filmServiceImpl;
    private GenreService genreService;
    private ParticipantsService  participantsService;
    private Logger logger = LoggerFactory.getLogger(FilmController.class);

    public FilmController(FilmService filmService, FilmService filmServiceImpl, GenreService genreService, ParticipantsService participantsService) {
        this.filmService = filmService;
        this.filmServiceImpl = filmServiceImpl;
        this.genreService = genreService;
        this.participantsService = participantsService;
    }

    @GetMapping({"/", "/accueil"})
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/films")
    public String getFilms(Model model) {
        List<Film> films = filmServiceImpl.consulterFilms();
        model.addAttribute("films", films);
        return "view-films";
    }

    @GetMapping("/films/detail")
    public String getFilmDetails(@RequestParam int id, Model model) {
        Film filmEntity = filmServiceImpl.consulterFilmParId(id);

        model.addAttribute("film", filmEntity);

        return "view-film-detail";
    }

    @GetMapping("/films/create")
    public String viewCreerFilm(Model model) {

        if (!model.containsAttribute("film")) {
            model.addAttribute("film", new FilmDTO());
        }

        return "view-creer-film";
    }

    @GetMapping("/films/delete")
    public String deleteFilm(@RequestParam int id) {
        filmService.supprimerFilmParId(id);

        return "redirect:/films";
    }

    @PostMapping("/films/create")
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
            return "redirect:/films/create";
        }

        Film newFilm = new Film();
        newFilm.setGenre(genreService.consulterGenreParId(filmDTO.getGenreId()));
        newFilm.setRealisateur(participantsService.consulterParticipantParId(filmDTO.getRealisateurId()));

        filmDTO.getActeursIds().forEach(id -> newFilm.getActeurs().add(participantsService.consulterParticipantParId(id)));

        // Récupère les propriétés identiques entre l'objet source (filmDto) et l'objet cible (newFilm)
        // et il les copie dans l'objet cible.
        BeanUtils.copyProperties(filmDTO, newFilm);
        filmServiceImpl.creerFilm(newFilm);

        return "redirect:/films";
    }
}
