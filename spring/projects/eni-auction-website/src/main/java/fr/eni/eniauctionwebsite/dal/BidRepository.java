package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Bid;

import java.util.List;
import java.util.Optional;

public interface BidRepository {
    List<Bid> getAllBids();
    Bid updateBid(Bid bid);
    Optional<Bid> getBidById(int id);
    void deleteBidById(int id);
    Bid findHighestBidByItemId(int itemId);
    List<Bid> getAllBidsByItemId(int itemId);
    List<Bid> findAllBidsByUserId(int userId);
    void deleteBidsByUserId(int userId);

}
