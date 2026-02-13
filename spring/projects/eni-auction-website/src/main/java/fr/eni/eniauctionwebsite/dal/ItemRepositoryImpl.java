package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.ItemCategory;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.auction.AuctionFilterDTO;
import fr.eni.eniauctionwebsite.exception.AddressNotFoundException;
import fr.eni.eniauctionwebsite.exception.ItemCategoryNotFoundException;
import fr.eni.eniauctionwebsite.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private ItemCategoryRepository itemCategoryRepository;
    private JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(JdbcTemplate jdbcTemplate, UserRepository userRepository, AddressRepository addressRepository, ItemCategoryRepository itemCategoryRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.itemCategoryRepository = itemCategoryRepository;
    }

    private class ItemRowMapper implements RowMapper<Item> {
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO: remplacer cette grosse triple requete sql par une vue sql
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setAuctionStartDate(rs.getTimestamp("auction_start_date").toLocalDateTime());
            item.setAuctionEndDate(rs.getTimestamp("auction_end_date").toLocalDateTime());
            item.setStartingPrice(rs.getInt("starting_price"));
            item.setCurrentPrice(rs.getInt("current_price"));
            item.setPriceSold(rs.getInt("price_sold"));
            item.setSold(rs.getBoolean("is_sold"));
            item.setRetrieved(rs.getBoolean("is_retrieved"));
            item.setImagePath(rs.getString("image_path"));
            User auctionCreator = null;
            try {
                auctionCreator = userRepository.getUserById(rs.getInt("user_id")).get();
            } catch (EmptyResultDataAccessException e) {
                throw new UserNotFoundException(rs.getString("user_id"));
            }
            item.setAuctionCreator(auctionCreator);

            Address address;
            try {
                address = addressRepository.getAddressById(rs.getInt("retrieval_address_id")).get();
            } catch (EmptyResultDataAccessException e) {
                throw new AddressNotFoundException(rs.getInt("retrieval_address_id"));
            }
            item.setRetrievalAddress(address);

            ItemCategory itemCategory;
            try {
                itemCategory = itemCategoryRepository.findItemCategoryById(rs.getInt("item_category_id")).get();
            } catch (EmptyResultDataAccessException e) {
                throw new ItemCategoryNotFoundException(rs.getInt("item_category_id"));
            }
            item.setCategory(itemCategory);

            return item;
        }
    }

    @Override
    public List<Item> findAllItems() {
        String sql = "SELECT * FROM Items"; // toutes les colonnes sont récupérées
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }


    @Override
    public Item saveItem(Item item) {

        if (item.getId() == 0) {

            String sql = """
                         INSERT INTO Items (name, description, auction_start_date, auction_end_date,
                    starting_price, current_price, user_id, item_category_id, retrieval_address_id, is_sold, is_retrieved, image_path)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            //Ajout du KeyHolder pour récupérer l'id généré par la base
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, item.getName());
                ps.setString(2, item.getDescription());
                ps.setTimestamp(3, Timestamp.valueOf(item.getAuctionStartDate()));
                ps.setTimestamp(4, Timestamp.valueOf(item.getAuctionEndDate()));
                ps.setInt(5, item.getStartingPrice());
                ps.setInt(6, item.getCurrentPrice());
                ps.setInt(7, item.getAuctionCreator().getId());
                ps.setInt(8, item.getCategory().getId());
                ps.setInt(9, item.getRetrievalAddress().getId());
                ps.setBoolean(10, false);
                ps.setBoolean(11, false);
                ps.setString(12, item.getImagePath());
                return ps;
            }, keyHolder);

            item.setId(keyHolder.getKey().intValue());

        } else {

            System.out.println("ITEM GET CURRENT PRICE (REPO EDITION): " + item.getCurrentPrice());
            System.out.println("ITEM ID: " + item.getId());

            String sql = """
                        UPDATE Items
                        SET name=?, description=?, auction_start_date=?, auction_end_date=?,
                            starting_price=?, price_sold=?, current_price=?, user_id=?, item_category_id=?, retrieval_address_id=?, is_sold=?, is_retrieved=?, image_path=?
                        WHERE id=?
                    """;

            jdbcTemplate.update(sql,
                    item.getName(),
                    item.getDescription(),
                    Timestamp.valueOf(item.getAuctionStartDate()),
                    Timestamp.valueOf(item.getAuctionEndDate()),
                    item.getStartingPrice(),
                    item.getPriceSold(),
                    item.getCurrentPrice(),
                    item.getAuctionCreator().getId(),
                    item.getCategory().getId(),
                    item.getRetrievalAddress().getId(),
                    item.isSold(),
                    item.isRetrieved(),
                    item.getImagePath(),
                    item.getId()
            );
        }

        return item;
    }


    @Override
    public Optional<Item> getItemById(int id) {
        String sql = "select * from items where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, new ItemRowMapper(), id));
    }

    @Override
    public void deleteItemById(int id) {
        String sqlget = "select * from items where id = ?";
        Item item = jdbcTemplate.query(sqlget, new ItemRowMapper(), id).getFirst();
        if (item != null) {
            String imagePath = item.getImagePath();
            if (!imagePath.equals("/images/default_image.png")) {
//              supprimer image
                Path path = Paths.get(System.getProperty("user.dir") + imagePath);
                try {
                    Files.delete(path);
                    System.out.println("File deleted successfully");
                } catch (IOException e) {
                    System.out.println("Failed to delete the file: " + e.getMessage());
                }
            }
        }

        String sql = "DELETE FROM Items WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Item> getItemsByCategoryId(int categoryId) {
        String sql = "SELECT * FROM Items WHERE item_category_id = ?";
        return jdbcTemplate.query(sql, new ItemRowMapper(), categoryId);
    }

    @Override
    public List<Item> getItemsByUserId(int userId) {
        String sql = "SELECT * FROM items WHERE user_id = ?";
        return jdbcTemplate.query(sql, new ItemRowMapper(), userId);
    }

    public void deleteItemsByUserId(int user_id) {
        String sql = "delete from items where user_id = ?";
        jdbcTemplate.update(sql, user_id);
    }

    // ---------------------------------------- FILTRAGE DYNAMIQUE -----------------------------------------------------

    @Override
    public List<Item> findItemsByFilters(AuctionFilterDTO filters, User user) {

        StringBuilder sql = new StringBuilder("""
                    SELECT DISTINCT i.*
                    FROM items i
                    LEFT JOIN bids b ON b.item_id = i.id
                    WHERE 1=1
                """);

        List<Object> params = new ArrayList<>();
        List<String> whereConditions = new ArrayList<>();

        // ------------------ Filtrage texte ------------------
        if (filters.getKeyword() != null && !filters.getKeyword().isBlank()) {
            whereConditions.add("(LOWER(i.name) LIKE ? OR LOWER(i.description) LIKE ?)");
            String keyword = "%" + filters.getKeyword().toLowerCase() + "%";
            params.add(keyword);
            params.add(keyword);
        }

        // ------------------ Filtrage catégorie ------------------
        if (filters.getCategories() != null && !"all".equals(filters.getCategories())) {
            whereConditions.add("i.item_category_id = ?");
            params.add(Integer.parseInt(filters.getCategories()));
        }

        // ------------------ Filtrage enchères / ventes ------------------
        String radioChoice = filters.getRadioChoiceAuctions();

        if ("auctions".equals(radioChoice)) {
            whereConditions.add("(" + buildAuctionConditions(filters, user, params) + ")");
        } else if ("sales".equals(radioChoice) && user != null) {
            whereConditions.add("i.user_id = ?");
            params.add(user.getId());
            whereConditions.add("(" + buildSalesConditions(filters) + ")");
        }

        if (!whereConditions.isEmpty()) {
            sql.append(" AND ").append(String.join(" AND ", whereConditions));
        }

        return jdbcTemplate.query(sql.toString(), new ItemRowMapper(), params.toArray());
    }


    // ------------------ Méthode privée pour créer les conditions sur les Achats  ------------------

    private String buildAuctionConditions(AuctionFilterDTO filters, User user, List<Object> params) {
        List<String> conditions = new ArrayList<>();

        boolean auctionsOpen = Boolean.TRUE.equals(filters.getAuctionsOpen());
        boolean myAuctions = Boolean.TRUE.equals(filters.getMyAuctions());
        boolean myAuctionsWon = Boolean.TRUE.equals(filters.getMyAuctionsWon());

        // Par défaut : enchères ouvertes ou à venir
        if (!auctionsOpen && !myAuctions && !myAuctionsWon) {
            conditions.add("(i.auction_start_date <= GETDATE() AND i.auction_end_date > GETDATE())");
            conditions.add("(i.auction_start_date > GETDATE())");

        } else {
            // Enchères ouvertes
            if (auctionsOpen) {
                conditions.add("(i.auction_start_date <= GETDATE() AND i.auction_end_date > GETDATE())");
                conditions.add("(i.auction_start_date > GETDATE())");
            }

            // Mes enchères (j'ai enchéri mais pas mes ventes)
            if (myAuctions && user != null) {
                conditions.add("""
                (
                    b.user_id = ?
                    AND i.user_id <> ?
                    AND i.auction_end_date > GETDATE()
                )
            """);
                params.add(user.getId());
                params.add(user.getId());
            }

            // Mes enchères remportées
            if (myAuctionsWon && user != null) {
                conditions.add("""
                            (
                            i.auction_end_date <= GETDATE()
                                AND i.is_sold = 1
                                AND i.user_id <> ?
                                AND b.user_id = ?
                                AND b.bid_amount = (
                                    SELECT MAX(b2.bid_amount)
                                    FROM bids b2
                                    WHERE b2.item_id = i.id
                                )
                            )
                        """);
                params.add(user.getId());
                params.add(user.getId());
            }
        }

        return "(" + String.join(" OR ", conditions) + ")";
    }


    // ------------------ Méthode privée pour créer les conditions sur les Ventes  ------------------

    private String buildSalesConditions(AuctionFilterDTO filters) {
        List<String> conditions = new ArrayList<>();

        boolean salesCurrent = Boolean.TRUE.equals(filters.getSalesCurrent());
        boolean salesNotStarted = Boolean.TRUE.equals(filters.getSalesNotStarted());
        boolean salesOver = Boolean.TRUE.equals(filters.getSalesOver());

        if (!salesCurrent && !salesNotStarted && !salesOver) {
            conditions.add("(i.is_sold = 0 AND i.auction_end_date > GETDATE())");
        } else {
            if (salesCurrent)
                conditions.add("(i.is_sold = 0 AND i.auction_end_date > GETDATE() AND i.auction_start_date <= GETDATE()) ");
            if (salesNotStarted) conditions.add("(i.auction_start_date > GETDATE())");
            if (salesOver) conditions.add("(i.auction_end_date <= GETDATE())");
        }

        return "(" + String.join(" OR ", conditions) + ")";
    }


}

