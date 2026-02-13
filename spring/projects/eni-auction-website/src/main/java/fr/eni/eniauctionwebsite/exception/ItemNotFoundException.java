package fr.eni.eniauctionwebsite.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int id) {
        super("Item with id " + id + " not found");
    }
}
