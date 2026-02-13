package fr.eni.eniauctionwebsite.exception;

public class CannotDeleteUserException extends RuntimeException {
    public CannotDeleteUserException(int item_id, String item_name, int userId) {
        super("User " + userId + " cannot delete because " + item_id + " " + item_name + " is still active.");
    }
}
