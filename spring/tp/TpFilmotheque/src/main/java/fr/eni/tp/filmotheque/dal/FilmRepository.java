package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface FilmRepository {
    List<Film> findAllFilms();
    Film findFilmById(Integer idFilm);
    void saveFilm(Film film);
//    void updateFilm(Film film);
    void deleteFilmById(int id);
}
