package fr.eni.eniauctionwebsite.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {
    private int id;
    private User auctionCreator;
    private String name;
    private String description;
    private LocalDateTime auctionStartDate;
    private LocalDateTime auctionEndDate;
    private int startingPrice;
    private int currentPrice;
    private int priceSold;
    private boolean isSold = false;
    private boolean isRetrieved = false;

    private List<Bid> bids = new ArrayList<>();
    private Address retrievalAddress;
    private ItemCategory category;

    private String imagePath;


    // CONSTRUCTORS
    public Item() {
    }

    public Item(User auctionCreator, String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate, int startingPrice, int priceSold, List<Bid> bids, Address retrievalAddress, ItemCategory category, boolean isSold, int currentPrice, boolean isRetrieved) {
        this.auctionCreator = auctionCreator;
        this.name = name;
        this.description = description;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.priceSold = priceSold;
        this.bids = bids;
        this.retrievalAddress = retrievalAddress;
        this.category = category;
        this.isSold = isSold;
        this.isRetrieved = isRetrieved;
    }
    public Item(int id, User auctionCreator, String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate, int startingPrice, int priceSold, List<Bid> bids, Address retrievalAddress, ItemCategory category, boolean isSold, int currentPrice, boolean isRetrieved) {
        this.id = id;
        this.auctionCreator = auctionCreator;
        this.name = name;
        this.description = description;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;
        this.priceSold = priceSold;
        this.bids = bids;
        this.retrievalAddress = retrievalAddress;
        this.category = category;
        this.isSold = isSold;
        this.isRetrieved = isRetrieved;
    }


    // EQUALS / HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && startingPrice == item.startingPrice && Objects.equals(auctionCreator, item.auctionCreator) && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(retrievalAddress, item.retrievalAddress) && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auctionCreator, name, description, startingPrice, retrievalAddress, category);
    }

    public boolean isAuctionOpen() {
        return auctionStartDate.isBefore(LocalDateTime.now()) && auctionEndDate.isAfter(LocalDateTime.now());
    }

    public boolean isAuctionNotStarted() {
        return auctionStartDate.isAfter(LocalDateTime.now());
    }

    public boolean isAuctionOver() {
        return auctionEndDate.isBefore(LocalDateTime.now());
    }

    // GETTERS / SETTERS
    public User getAuctionCreator() {
        return auctionCreator;
    }
    public void setAuctionCreator(User auctionCreator) {
        this.auctionCreator = auctionCreator;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getAuctionStartDate() {
        return auctionStartDate;
    }
    public void setAuctionStartDate(LocalDateTime auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }
    public LocalDateTime getAuctionEndDate() {
        return auctionEndDate;
    }
    public void setAuctionEndDate(LocalDateTime auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }
    public int getStartingPrice() {
        return startingPrice;
    }
    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }
    public int getPriceSold() {
        return priceSold;
    }
    public void setPriceSold(int priceSold) {
        this.priceSold = priceSold;
    }
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    public Address getRetrievalAddress() {
        return retrievalAddress;
    }
    public void setRetrievalAddress(Address retrievalAddress) {
        this.retrievalAddress = retrievalAddress;
    }
    public ItemCategory getCategory() {
        return category;
    }
    public void setCategory(ItemCategory category) {
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isSold() {
        return isSold;
    }
    public void setSold(boolean sold) {
        isSold = sold;
    }
    public int getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
    public boolean isRetrieved() {
        return isRetrieved;
    }
    public void setRetrieved(boolean retrieved) {
        isRetrieved = retrieved;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
