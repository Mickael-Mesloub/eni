package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> consulterGenres();
    public Genre consulterGenreParId(int id);
    void creerGenre(Genre genre);
    void updateGenre(int id, String titre);
    void deleteGenreById(int id);
}
