package fr.eni.eniauctionwebsite.controller.dto.auction;

public class AuctionFilterDTO {

    private String keyword;
    private String categories;
    private String radioChoiceAuctions = "auctions";

    private Boolean auctionsOpen;
    private Boolean myAuctions;
    private Boolean myAuctionsWon;

    private Boolean salesCurrent;
    private Boolean salesNotStarted;
    private Boolean salesOver;

    // getters / setters

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getRadioChoiceAuctions() {
        return radioChoiceAuctions;
    }

    public void setRadioChoiceAuctions(String radioChoiceAuctions) {
        this.radioChoiceAuctions = radioChoiceAuctions;
    }

    public Boolean getAuctionsOpen() {
        return auctionsOpen;
    }

    public void setAuctionsOpen(Boolean auctionsOpen) {
        this.auctionsOpen = auctionsOpen;
    }

    public Boolean getMyAuctions() {
        return myAuctions;
    }

    public void setMyAuctions(Boolean myAuctions) {
        this.myAuctions = myAuctions;
    }

    public Boolean getMyAuctionsWon() {
        return myAuctionsWon;
    }

    public void setMyAuctionsWon(Boolean myAuctionsWon) {
        this.myAuctionsWon = myAuctionsWon;
    }

    public Boolean getSalesCurrent() {
        return salesCurrent;
    }

    public void setSalesCurrent(Boolean salesCurrent) {
        this.salesCurrent = salesCurrent;
    }

    public Boolean getSalesNotStarted() {
        return salesNotStarted;
    }

    public void setSalesNotStarted(Boolean salesNotStarted) {
        this.salesNotStarted = salesNotStarted;
    }

    public Boolean getSalesOver() {
        return salesOver;
    }

    public void setSalesOver(Boolean salesOver) {
        this.salesOver = salesOver;
    }
}

