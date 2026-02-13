package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.dal.BidRepository;
import fr.eni.eniauctionwebsite.dal.ItemRepository;
import fr.eni.eniauctionwebsite.dal.UserRepository;
import fr.eni.eniauctionwebsite.exception.BidNotFoundException;
import fr.eni.eniauctionwebsite.exception.ItemCategoryNotFoundException;
import fr.eni.eniauctionwebsite.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {
    JdbcTemplate jdbcTemplate;
    BidRepository bidRepository;
    ItemRepository itemRepository;
    UserRepository userRepository;

    @Autowired
    public BidServiceImpl(JdbcTemplate jdbcTemplate, BidRepository bidRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.getAllBids();
    }

    @Override
    public Bid updateBid(Bid bid) {
        Item item = bid.getAuctionItem();
        if (bid.getBidAmount() > item.getCurrentPrice()) {
            bid.setHighestBid(true);
            item.setCurrentPrice(bid.getBidAmount());
            item.getBids().add(bid);
            itemRepository.saveItem(item);
            return bidRepository.updateBid(bid);
        }
        return bid;
    }

    @Override
    public Bid getBidById(int id) {
        return bidRepository.getBidById(id)
                .orElseThrow(() -> new BidNotFoundException(id));
    }

    @Override
    public void deleteBidById(int id) {
        bidRepository.deleteBidById(id);
    }

    @Override
    public Bid findHighestBidByItemId(int itemId) {
        return bidRepository.findHighestBidByItemId(itemId);
    }

    @Override
    public List<Bid> findAllBidsByUserId(int userId) {
        return bidRepository.findAllBidsByUserId(userId);
    }

    @Override
    public List<Bid> findAllBidsByItemId(int itemId) {
        return bidRepository.getAllBidsByItemId(itemId);
    }

    @Override
    public int getUpdatedCreditAmount(Item item, User user, int bidAmount) {
        if (!item.getBids().isEmpty()) {
            Bid oldBid = bidRepository.findHighestBidByItemId(item.getId());
            User oldUser = oldBid.getBidder();

            if (oldUser.getId() == user.getId()) {
                return user.getCreditPoints() - bidAmount + oldBid.getBidAmount();
            }
            oldUser.setCreditPoints(oldUser.getCreditPoints() + oldBid.getBidAmount());
            userRepository.updateUser(oldUser);
            return user.getCreditPoints() - bidAmount;
        }
        return user.getCreditPoints();
    }

    @Override
    @Transactional
    public Bid placeBid(int bidAmount, User user, int itemId) {
//        verifier si l'utilisateur a assez de points
        if (bidAmount <= user.getCreditPoints()) {
            Bid newBid = new Bid();
            Item item = itemRepository.getItemById(itemId).get();
            item.setBids(findAllBidsByItemId(itemId));

//            s'il y a deja une enchère, remet les points à jour sur le compte de l'utilisateur qui avait l'ancienne enchère la plus élevée
            int newCreditAmount = getUpdatedCreditAmount(item, user, bidAmount);

            user.setCreditPoints(newCreditAmount);

//            remplir les infos de la nouvelle enchère créée
            newBid.setBidDate(LocalDateTime.now());
            newBid.setBidAmount(bidAmount);
            newBid.setBidder(user);
            newBid.setAuctionItem(item);

//            mettre à jour les infos sur la base
//            TODO: remplacer par une vue sql quand on aura un moment
            userRepository.updateUser(user);
            itemRepository.saveItem(item);
            return newBid;
        }
        return null;
    }

    @Override
    public void deleteBidsByUserId(int userId){
        bidRepository.deleteBidsByUserId(userId);
    }

    @Override
    public List<Bid> getActiveBidsFromUser(List<Bid> bids, User user){
        List<Bid> activeBids = new ArrayList<>();
        bids.forEach(bid -> {
            if(!bid.getAuctionItem().isRetrieved()) {
                int itemId = bid.getAuctionItem().getId();
                Bid hightestBid = findHighestBidByItemId(itemId);
                if(hightestBid.getBidder().getId() == user.getId()) {
                    activeBids.add(bid);
                }
            }
        });
        return activeBids;
    }
}

