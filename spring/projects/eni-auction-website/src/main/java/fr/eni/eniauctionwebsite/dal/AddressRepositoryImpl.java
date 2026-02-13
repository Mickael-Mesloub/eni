package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private JdbcTemplate jdbcTemplate;

    public AddressRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private class AddressRowMapper implements RowMapper<Address> {
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setZipcode(rs.getString("zipcode"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
        return address;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        String sql = "select id, zipcode, street, city from address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    @Override
    public Address createAddress(Address address) {
        String sql;
        if (address.getId() == 0){
            sql = "INSERT INTO address (zipcode,street,city) VALUES (?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, address.getZipcode());
                    ps.setString(2, address.getStreet());
                    ps.setString(3, address.getCity());
                    return ps;
                }
            };
            jdbcTemplate.update(psc, keyHolder);
            address.setId(keyHolder.getKey().intValue());
        }
        else{
            sql = "update address set zipcode = ?, street = ?, city = ? where id = ?";
            jdbcTemplate.update(sql, address.getZipcode(), address.getStreet(), address.getCity(), address.getId());
        }
        return address;
    }

    @Override
    public Optional<Address> getAddressById(int id) {
        String sql =  "SELECT id, zipcode, street, city FROM address WHERE id = ?";
        try{
            Address address = jdbcTemplate.queryForObject(sql, new AddressRowMapper(), id);
            return Optional.of(address);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Address> getAddressByStreetAndCityAndZipcode(String street, String city, String zipcode) {
        String sql =  "SELECT id, zipcode, street, city FROM address WHERE zipcode = ? AND street = ? AND city = ?";

        try{
            Address address = jdbcTemplate.queryForObject(sql, new AddressRowMapper(), zipcode, street, city);
            return Optional.of(address);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteAddressById(int id) {
        String sql =  "DELETE FROM address WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}