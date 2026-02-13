package fr.eni.eniauctionwebsite.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(int id) {
        super("Address with id " + id + " not found");
    }

    public AddressNotFoundException(String street, String city, String zipcode) {
        super("Address not found: " + street + ", " + city + ", " + zipcode);
    }
}
