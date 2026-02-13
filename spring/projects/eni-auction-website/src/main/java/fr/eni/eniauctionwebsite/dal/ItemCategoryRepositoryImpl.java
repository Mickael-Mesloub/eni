package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.ItemCategory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemCategoryRepositoryImpl implements ItemCategoryRepository {

    private JdbcTemplate jdbcTemplate;
    public ItemCategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemCategory> findAllItemCategories() {
        String sql = "select id, description from ItemCategories";
        List<ItemCategory> itemCategories = jdbcTemplate.query(sql, new ItemCategoryRowMapper());
        return itemCategories;
    }

    @Override
    public Optional<ItemCategory> findItemCategoryById(int id) {
        String sql = "select id, description from ItemCategories where id = ?";
        try {
            ItemCategory itemCategory = jdbcTemplate.queryForObject(sql, new ItemCategoryRowMapper(), id);
            return Optional.of(itemCategory);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    //Classe interne
    class ItemCategoryRowMapper implements RowMapper<ItemCategory> {

        @Override
        public ItemCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ItemCategory  itemCategory = new ItemCategory();
            itemCategory.setId(rs.getInt("id"));
            itemCategory.setDescription(rs.getString("description"));
            return itemCategory;
        }
    }

}
