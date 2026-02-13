package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.exception.BidNotFoundException;
import fr.eni.eniauctionwebsite.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidRepositoryTest {

    UserRepository userRepository;
    BidRepository bidRepository;
    ItemRepository itemRepository;
    JdbcTemplate jdbcTemplate;
    @Autowired
    public BidRepositoryTest(JdbcTemplate jdbcTemplate, UserRepository userRepository, BidRepository bidRepository, ItemRepository itemRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
    }

    @Test
    public void createBidSuccess() {
        Bid bid = new Bid();
        bid.setBidDate(LocalDateTime.now());
        bid.setBidAmount(500);
        User user = userRepository.getUserById(1).get();
        bid.setBidder(user);
        Item item = itemRepository.getItemById(1).get();
        bid.setAuctionItem(item);

        bidRepository.updateBid(bid);

        assertNotNull(bidRepository.getBidById(bid.getId()));

    }

    @Test
    public void getBidByIdFound(){
        Bid bid = bidRepository.getBidById(1).get();

        assertNotNull(bidRepository.getBidById(1));
        assertEquals(1, bid.getAuctionItem().getId());
        assertEquals(300, bid.getBidAmount());
    }

    @Test
    public void getAllBids(){
        List<Bid> bids = bidRepository.getAllBids();
        assertNotNull(bidRepository.getAllBids());
        assertEquals(3, bids.size());
    }

    @Test
    public void deleteBidById(){
        bidRepository.deleteBidById(1);
        List<Bid> bids = bidRepository.getAllBids();
        assertEquals(2, bids.size());
        assertFalse(bidRepository.getBidById(1).isPresent());

    }
}
