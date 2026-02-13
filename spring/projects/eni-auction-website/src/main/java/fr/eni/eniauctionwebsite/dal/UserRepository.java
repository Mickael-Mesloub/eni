package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    void updateUser(User user);
    void deleteUserById(int id);
}
