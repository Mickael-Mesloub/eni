package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;

import java.util.List;
import java.util.Optional;

public interface BidService {
    List<Bid> getAllBids();
    Bid updateBid(Bid bid);
    Bid getBidById(int id);
    void deleteBidById(int id);
    Bid findHighestBidByItemId(int itemId);
    List<Bid> findAllBidsByItemId(int itemId);
    int getUpdatedCreditAmount(Item item, User user, int bidAmount);
    Bid placeBid(int bidAmount, User user, int itemId);
    List<Bid> findAllBidsByUserId(int userId);
    void deleteBidsByUserId(int userId);
    List<Bid> getActiveBidsFromUser(List<Bid> bids, User user);
}
