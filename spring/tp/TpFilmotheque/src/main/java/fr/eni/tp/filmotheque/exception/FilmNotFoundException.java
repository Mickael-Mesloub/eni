package fr.eni.tp.filmotheque.exception;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(int filmId) {
        super("Film id " + filmId + " not found");
    }
}
