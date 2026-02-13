package fr.eni.eniauctionwebsite.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User not found with id " + id);
    }

    public UserNotFoundException(String message) {
        super("User not found: " + message + " does not exist.");
    }
}
