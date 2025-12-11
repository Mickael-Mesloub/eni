package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    List<Film> consulterFilms();
    Film consulterFilmParId(int id);
    List<Participant> consulterParticipants();
    Participant consulterParticipantParId(int id);
    void creerFilm(Film film);
    void supprimerFilmParId(int id);
}
