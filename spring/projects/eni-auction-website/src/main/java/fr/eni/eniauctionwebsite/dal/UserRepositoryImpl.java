package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private AddressRepository addressRepository;

    //    private ItemRepository itemRepository;
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, AddressRepository addressRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressRepository = addressRepository;
//        this.itemRepository = itemRepository;
    }

    private class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setLastname(rs.getString("lastname"));
            user.setFirstname(rs.getString("firstname"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setPassword(rs.getString("password"));
            user.setCreditPoints(rs.getInt("credit_points"));
            user.setAdmin(rs.getBoolean("is_admin"));

            Address address = new Address(rs.getInt("address_id"),
                    rs.getString("street"),
                    rs.getString("zipcode"),
                    rs.getString("city")
            );
            user.setAddress(address);

            return user;
        }
    }


    @Override
    public List<User> getAllUsers() {
        String sql = "select Users.id, username, lastname, firstname, email," +
                "phone, address_id, password, credit_points, is_admin, street, city, zipcode from users " +
                "inner join Address on address.id = users.address_id";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(int id) {
        String sql = "SELECT Users.id, username, lastname, firstname, email," +
                "    phone, address_id, password, credit_points, is_admin, street, zipcode, city FROM Users" +
                "    inner join Address ON Users.address_id = Address.id where Users.id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        String sql = "SELECT Users.id, username, lastname, firstname, email," +
                "    phone, address_id, password, credit_points, is_admin, street, zipcode, city FROM Users" +
                "    inner join Address ON Users.address_id = Address.id where username = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT Users.id, username, lastname, firstname, email," +
                "    phone, address_id, password, credit_points, is_admin, street, zipcode, city FROM Users" +
                "    inner join Address ON Users.address_id = Address.id where email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getId() == 0) {
            String sql = "insert into Users (username, lastname, firstname, email, " +
                    "phone, address_id, password, credit_points, is_admin)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            TODO : optimiser sous forme d'une seule requete SQL???
//            TODO : es ce qu'il faut un check ICI avant d'envoyer un doublon à la base??? (en vérifiant que le USERNAME soit pas pris)

            try {

                if (addressRepository.getAddressById(user.getAddress().getId()).isEmpty()) {
                    Address address = addressRepository.createAddress(user.getAddress());
                    user.setAddress(address);
                }

                KeyHolder keyHolder = new GeneratedKeyHolder();
                PreparedStatementCreator psc = new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        ps.setString(1, user.getUsername());
                        ps.setString(2, user.getLastname());
                        ps.setString(3, user.getFirstname());
                        ps.setString(4, user.getEmail());
                        ps.setString(5, user.getPhone());
                        ps.setInt(6, user.getAddress().getId());
                        ps.setString(7, user.getPassword());
                        ps.setInt(8, user.getCreditPoints());
                        ps.setBoolean(9, user.isAdmin());

                        return ps;
                    }
                };

                jdbcTemplate.update(psc, keyHolder);

                user.setId(keyHolder.getKey().intValue());

            } catch (DuplicateKeyException ex) {
                String msg = ex.getMostSpecificCause().getMessage();

                // Check if username already exists
                if (msg.contains("users_username_uk")) {
                    throw new UserAlreadyExistsException(true, false);
                }
                // Check if email already exists
                if (msg.contains("users_email_uk")) {
                    throw new UserAlreadyExistsException(false, true);
                }
                throw ex;
            }
        }

        else {
                String sqlUpdateUser = "update Users set username = ?, lastname = ?, firstname = ?, email = ?, " +
                        "phone = ?, address_id = ?, password= ?, credit_points= ?, is_admin=? where id = ?";
                jdbcTemplate.update(sqlUpdateUser, user.getUsername(), user.getLastname(), user.getFirstname(), user.getEmail(),
                        user.getPhone(), user.getAddress().getId(), user.getPassword(), user.getCreditPoints(), user.isAdmin(), user.getId());

            System.out.println(user.getAddress().getStreet() + " " + user.getAddress().getZipcode() + " " + user.getAddress());

                String sqlUpdateAddress = "update Address set street = ?, zipcode = ?, city = ? where id = ?";
                jdbcTemplate.update(sqlUpdateAddress, user.getAddress().getStreet(), user.getAddress().getZipcode(), user.getAddress().getCity(), user.getId());
            }
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "delete from Users where id= ?";
        jdbcTemplate.update(sql, id);
    }
}
