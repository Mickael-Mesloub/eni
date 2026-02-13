package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByLogin(String login);
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(int id);
}
