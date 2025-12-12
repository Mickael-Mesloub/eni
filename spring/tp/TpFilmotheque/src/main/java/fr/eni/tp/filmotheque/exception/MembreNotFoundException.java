package fr.eni.tp.filmotheque.exception;

public class MembreNotFoundException extends RuntimeException {
    public MembreNotFoundException(String username) {
        super("Membre not found with username " + username);
    }
}
