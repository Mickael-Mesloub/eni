package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service("filmServiceImpl")
public class FilmServiceImpl implements FilmService {
    private GenreRepository genreRepository;
    private FilmRepository filmRepository;

    public FilmServiceImpl(GenreRepository genreRepository, FilmRepository filmRepository) {
        this.genreRepository = genreRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> consulterFilms() {
        return filmRepository.findAllFilm();
    }

    @Override
    public Film consulterFilmParId(int id) {
        return filmRepository.findFilmById(id);
    }

    @Override
    public List<Participant> consulterParticipants() {
        return List.of();
    }

    @Override
    public Participant consulterParticipantParId(int id) {
        return null;
    }

    @Override
    public void creerFilm(Film film) {
        filmRepository.createFilm(film);
    }
}
