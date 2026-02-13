package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;

public interface AuctionService {
    void auctionEndMessage(Item item);
    boolean isAuctionEnded(Item item);
    void concludeAuction(int itemId);
    int updateCurrentPrice(Bid highestBid, Item item);
    User getHighestBidder(Bid highestBid, Item item);
}
