package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.controller.dto.GenreDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {
   private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String getGenres(Model model) {
        List<Genre> genres = genreService.consulterGenres();
        model.addAttribute("genres", genres);
        return "view-genres";
    }

    @GetMapping("/create")
    public String viewCreerGenre(Model model) {

        if (!model.containsAttribute("genre")) {
            model.addAttribute("genre", new GenreDTO());
        }
        return "view-creer-genre";
    }

    @GetMapping("/update")
    public String viewUpdateGenre(@RequestParam int id, Model model) {
        Genre genreEntity = genreService.consulterGenreParId(id);
        model.addAttribute("genre", genreEntity);
        return "view-update-genre";
    }

    @GetMapping("/delete")
    public String deleteGenre(@RequestParam int id) {
        genreService.deleteGenreById(id);

        return "redirect:/genres";
    }

    @PostMapping("/create")
    public String creerGenre(
            @Valid @ModelAttribute("genre") GenreDTO genreDTO,
            BindingResult resultat,
            Model model,
            RedirectAttributes redirectAttr
    ) {
        // TODO message d'erreur si le titre existe déjà
        if (resultat.hasErrors()) {
            // Les redirectAttr servent à renvoyer les erreurs (resultat) et les précédentes saisies utilisateur (filmDTO)
            // dans la nouvelle requête lors du redirect dans le return.
            redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.genre", resultat);
            redirectAttr.addFlashAttribute("genre", genreDTO);
            return "redirect:/genres/create";
        }

        Genre newGenre = new Genre();
        newGenre.setTitre(genreDTO.getTitre());

        BeanUtils.copyProperties(genreDTO, newGenre);

        genreService.creerGenre(newGenre);

        return "redirect:/genres";
    }

    @PostMapping("/update")
    public String modifierGenre(
            @Valid @ModelAttribute("genre") GenreDTO genreDto,
            BindingResult resultat,
            Model model,
            RedirectAttributes redirectAttr
    ) {
        // TODO message d'erreur si le titre existe déjà

        if (resultat.hasErrors()) {
            redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.genre", resultat);
            redirectAttr.addFlashAttribute("genre", genreDto);
            return "redirect:/genres/update?id=" + genreDto.getId();
        }

        genreService.updateGenre(genreDto.getId(), genreDto.getTitre());

        return "redirect:/genres";
    }

}
