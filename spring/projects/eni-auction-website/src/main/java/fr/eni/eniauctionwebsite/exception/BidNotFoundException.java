package fr.eni.eniauctionwebsite.exception;

public class BidNotFoundException extends RuntimeException {
    public BidNotFoundException(int id) {
        super("Bid not found with id " + id);
    }
}
