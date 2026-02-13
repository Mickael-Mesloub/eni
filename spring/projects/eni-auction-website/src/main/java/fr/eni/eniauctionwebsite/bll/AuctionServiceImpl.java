package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.dal.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuctionServiceImpl implements AuctionService {

    ItemService itemService;
    UserService userService;

    public AuctionServiceImpl(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public void auctionEndMessage(Item item) {
//        si on envoie un message aux utilisateurs concernés (le vendeur et le meilleur encherisseur):
//        if auction end date is passed: (using ScheduledExecutorService from this post: https://stackoverflow.com/questions/17848859/how-to-run-a-specific-method-on-a-specific-date-and-time )
//          get the highest bidding user and send a "you won the auction" message.
//          send "auction ended" message to auctionCreator
    }

    @Override
    public boolean isAuctionEnded(Item item) {
        if (item.getAuctionEndDate().isBefore(LocalDateTime.now())) {
//            rajoute le prix de vente final
            item.setPriceSold(item.getCurrentPrice());
//             afficher l'écran de fin d'enchère
            return true;
        } else {
//            afficher la page habituelle des détails de l'enchère
            return false;
        }
    }

    @Override
    public void concludeAuction(int itemId) {
        Item item = itemService.findItemById(itemId);
        User auctionCreator = item.getAuctionCreator();
        if(!item.isRetrieved()){
            auctionCreator.setCreditPoints(auctionCreator.getCreditPoints() + item.getCurrentPrice());
            item.setRetrieved(true);
            userService.updateUser(auctionCreator);
            itemService.saveItem(item);
        }
    }

    @Override
    public int updateCurrentPrice(Bid highestBid, Item item) {
        int currentPrice;
        User highestBidder = null;
        if (highestBid != null) {
            currentPrice = highestBid.getBidAmount();
            highestBidder = highestBid.getBidder();
        } else {
            currentPrice = item.getStartingPrice();
            highestBidder = item.getAuctionCreator();
        }
        return currentPrice;
    }

    @Override
    public User getHighestBidder(Bid highestBid, Item item) {
        User highestBidder = null;
        if (highestBid != null) {
            highestBidder = highestBid.getBidder();
        } else {
            highestBidder = item.getAuctionCreator();
        }
        return highestBidder;
    }
}
