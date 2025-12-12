package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GenreServiceImpl implements GenreService{
    GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> consulterGenres() {
        return genreRepository.findAllGenres();
    }

    @Override
    public Genre consulterGenreParId(int id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public void creerGenre(Genre genre) {
        List<Integer> genreIds = new ArrayList<>();
        genreRepository.findAllGenres().forEach(g -> genreIds.add(g.getId()));
        Integer maxId = genreIds.stream().max(Integer::compareTo).orElse(0);
        genre.setId(maxId + 1);
        genreRepository.createGenre(genre);
    }

    @Override
    public void updateGenre(int id, String nouveauTitre) {
        Genre genre = genreRepository.findGenreById(id);
        genre.setTitre(nouveauTitre);

        genreRepository.updateGenre(genre);
    }

    @Override
    public void deleteGenreById(int id) {
        Genre genre = genreRepository.findGenreById(id);
        genreRepository.deleteGenreById(id);
    }
}
