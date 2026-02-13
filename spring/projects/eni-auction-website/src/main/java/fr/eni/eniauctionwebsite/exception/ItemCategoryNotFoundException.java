package fr.eni.eniauctionwebsite.exception;

public class ItemCategoryNotFoundException extends RuntimeException {

    public ItemCategoryNotFoundException(int id) {
        super("ItemCategory with id " + id + " not found");
    }

}
