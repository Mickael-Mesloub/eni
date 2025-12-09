package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FilmRepository {
    List<Film> findAllFilm();
//    Film findFilmById(int id);
//    void createFilm(Film film);
//    void updateFilm(Film film);
//    void deleteFilmById(int id);
}
