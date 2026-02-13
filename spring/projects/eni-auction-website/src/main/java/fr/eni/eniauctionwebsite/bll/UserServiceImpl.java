package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.dal.ItemRepository;
import fr.eni.eniauctionwebsite.dal.UserRepository;
import fr.eni.eniauctionwebsite.exception.CannotDeleteUserException;
import fr.eni.eniauctionwebsite.exception.ItemCategoryNotFoundException;
import fr.eni.eniauctionwebsite.exception.UserAlreadyExistsException;
import fr.eni.eniauctionwebsite.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{
    ItemRepository itemRepository;
    UserRepository userRepository;
    PasswordEncoder  passwordEncoder;

    public UserServiceImpl(ItemRepository itemRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User getUserByLogin(String login) {
        return (login.contains("@")) ? getUserByEmail(login) : getUserByUsername(login);
    }


    @Override
    public void createUser(User user) {
        boolean usernameExists = userRepository.getUserByUsername(user.getUsername()).isPresent();
        boolean emailExists = userRepository.getUserByEmail(user.getEmail()).isPresent();

        // Throw exception and passing booleans in argument if username and/or email already exist
        if (usernameExists || emailExists) {
            throw new UserAlreadyExistsException(usernameExists, emailExists);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userRepository.updateUser(user);
        } catch (DuplicateKeyException ex) {
            throw new UserAlreadyExistsException(true, true);
        }
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        itemRepository.getItemsByUserId(id).forEach(item -> {
            if(!item.isSold()){
                throw new CannotDeleteUserException(item.getId(), item.getName(), id);
            }
        });
        itemRepository.deleteItemsByUserId(id);
        userRepository.deleteUserById(id);
    }
}
