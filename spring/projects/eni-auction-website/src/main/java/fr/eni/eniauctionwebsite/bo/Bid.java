package fr.eni.eniauctionwebsite.bo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Bid {
    private int id;
    private LocalDateTime bidDate;
    private int bidAmount;
    private boolean isHighestBid = false;

    private User bidder;
    private Item auctionItem;

    // CONSTRUCTORS
    public Bid() {
    }
    public Bid(
            LocalDateTime bidDate,
            int bidAmount,
            User bidder,
            Item auctionItem,
            boolean isHighestBid) {
        this.bidDate = bidDate;
        this.bidAmount = bidAmount;
        this.bidder = bidder;
        this.auctionItem = auctionItem;
        this.isHighestBid = isHighestBid;

    }
    public Bid(int id, LocalDateTime bidDate, int bidAmount, User bidder, Item auctionItem, boolean isHighestBid) {
        this.id = id;
        this.bidDate = bidDate;
        this.bidAmount = bidAmount;
        this.bidder = bidder;
        this.auctionItem = auctionItem;
        this.isHighestBid = isHighestBid;
    }

    // EQUALS / HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id && bidAmount == bid.bidAmount && isHighestBid == bid.isHighestBid && Objects.equals(bidDate, bid.bidDate) && Objects.equals(bidder, bid.bidder) && Objects.equals(auctionItem, bid.auctionItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bidDate, bidAmount, isHighestBid, bidder, auctionItem);
    }

    // GETTERS / SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getBidDate() {
        return bidDate;
    }
    public void setBidDate(LocalDateTime bidDate) {
        this.bidDate = bidDate;
    }
    public int getBidAmount() {
        return bidAmount;
    }
    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }
    public User getBidder() {
        return bidder;
    }
    public void setBidder(User bidder) {
        this.bidder = bidder;
    }
    public Item getAuctionItem() {
        return auctionItem;
    }
    public void setAuctionItem(Item auctionItem) {
        this.auctionItem = auctionItem;
    }
    public boolean isHighestBid() {
        return isHighestBid;
    }
    public void setHighestBid(boolean highestBid) {
        isHighestBid = highestBid;
    }
}
