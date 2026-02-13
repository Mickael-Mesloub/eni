package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.exception.ItemNotFoundException;
import fr.eni.eniauctionwebsite.exception.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BidRepositoryImpl implements BidRepository {
    JdbcTemplate jdbcTemplate;
    ItemRepository itemRepository;
    UserRepository userRepository;

    public BidRepositoryImpl(JdbcTemplate jdbcTemplate, ItemRepository itemRepository, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    private class BidRowMapper implements RowMapper<Bid> {

        @Override
        public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bid bid = new Bid();
            bid.setId(rs.getInt("id"));
            bid.setBidDate(rs.getTimestamp("bid_date").toLocalDateTime());
            bid.setBidAmount(rs.getInt("bid_amount"));
            int itemId = rs.getInt("item_id");
            Item item = itemRepository.getItemById(itemId)
                    .orElseThrow(() -> new ItemNotFoundException(itemId));
            bid.setAuctionItem(item);
            int userId = rs.getInt("user_id");
            User bidder = userRepository.getUserById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            bid.setBidder(bidder);
            return bid;
        }
    }

    @Override
    public List<Bid> getAllBids() {
        String sql = "SELECT id, bid_date, bid_amount, item_id, user_id FROM Bids";
        return jdbcTemplate.query(sql, new BidRowMapper());
    }

    @Override
    public Bid updateBid(Bid bid) {
//        TODO: remplacer le try catch par un autre truc qui marche (peut être le formatter pour qu'il soit plus lisible)
        Bid foundBid = null;
        try {
            String sqlSelect = "SELECT * FROM Bids WHERE item_id = ? and user_id = ?";
            foundBid = jdbcTemplate.queryForObject(sqlSelect, new BidRowMapper(), bid.getAuctionItem().getId(), bid.getBidder().getId());
        } catch (EmptyResultDataAccessException ex) {
            String sqlInsert = "insert into Bids (bid_date, bid_amount, item_id, user_id)" +
                    "values (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[]{"id"});
                    ps.setTimestamp(1, Timestamp.valueOf(bid.getBidDate()));
                    ps.setInt(2, bid.getBidAmount());
                    ps.setInt(3, bid.getAuctionItem().getId());
                    ps.setInt(4, bid.getBidder().getId());
                    return ps;
                }
            };
            jdbcTemplate.update(psc, keyHolder);
            bid.setId(keyHolder.getKey().intValue());
            return bid;
        }
        String sqlUpdate = "update Bids set bid_date=?, bid_amount=?, item_id=? where id=? and user_id=?";
        jdbcTemplate.update(sqlUpdate, Timestamp.valueOf(bid.getBidDate()), bid.getBidAmount(), foundBid.getAuctionItem().getId(), foundBid.getId(), foundBid.getBidder().getId());
        return bid;
    }


    @Override
    public Optional<Bid> getBidById(int id) {
        String sql = "SELECT id, bid_date, bid_amount, item_id, user_id FROM Bids WHERE id = ?";
        try {
            Bid bid = jdbcTemplate.queryForObject(sql, new BidRowMapper(), id);
            return Optional.of(bid);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteBidById(int id) {
        String sql = "DELETE FROM Bids WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Bid findHighestBidByItemId(int itemId) {
        String sql = """
        SELECT TOP 1 *
               FROM Bids
               WHERE item_id = ?
               ORDER BY bid_amount DESC;
    """;

        List<Bid> bids = jdbcTemplate.query(sql, new BidRowMapper(), itemId);

        return bids.isEmpty() ? null : bids.get(0);
    }

    @Override
    public List<Bid> getAllBidsByItemId(int itemId) {
        String sql = "SELECT id, bid_date, bid_amount, item_id, user_id FROM Bids where item_id = ?;";
        return jdbcTemplate.query(sql, new BidRowMapper(), itemId);
    }

    @Override
    public List<Bid> findAllBidsByUserId(int userId) {
        String sql = "SELECT id, bid_date, bid_amount, item_id, user_id FROM Bids WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BidRowMapper());
    }


    @Override
    public void deleteBidsByUserId(int userId){
        String sql = "DELETE FROM Bids WHERE user_id = ?;";
        jdbcTemplate.update(sql, userId);
    }
}
