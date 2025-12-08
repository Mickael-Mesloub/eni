package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.exception.GenreNotFoundException;
import fr.eni.tp.filmotheque.exception.GenreTitreAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service("filmServiceImpl")
public class FilmServiceImpl implements FilmService {
    private GenreRepository genreRepository;

    @Autowired
    public FilmServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Film> consulterFilms() {
        return List.of();
    }

    @Override
    public Film consulterFilmParId(int id) {
        return null;
    }

    @Override
    public List<Genre> consulterGenres() {
        return genreRepository.findAllGenres();
    }

    @Override
    public List<Participant> consulterParticipants() {
        return List.of();
    }

    @Override
    public Genre consulterGenreParId(int id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public Participant consulterParticipantParId(int id) {
        return null;
    }

    @Override
    public void creerFilm(Film film) {

    }

    @Override
    public void creerGenre(Genre genre) {
        genre.setId(genreRepository.findAllGenres().size() +1);
        genreRepository.createGenre(genre);
    }

    @Override
    public void updateGenre(int id, String titre) {
        Genre genre = genreRepository.findGenreById(id);
        genre.setTitre(titre);
        genreRepository.updateGenre(genre);
    }
}
