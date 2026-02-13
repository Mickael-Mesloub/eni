package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

//    TODO: trouver comment lancer un script sql en "before each"

    @Test
    public void getAllUsersTest(){
        List<User> users = userRepository.getAllUsers();
        assertEquals(3,  users.size());
    }


    @Test
    public void createUserTest(){
        User bob = new User();
        bob.setAdmin(true);
        bob.setUsername("bob");
        bob.setLastname("zerty");
        bob.setFirstname("bob");
        bob.setEmail("bob@zerty");
        bob.setPhone("1234567890");
        bob.setAddress(new Address("40, rue bob", "48000", "St Herblain"));
        bob.setPassword("azerty");
        bob.setCreditPoints(1000);


        userRepository.updateUser(bob);
        User userGot = userRepository.getUserByUsername("bob").get();
        assertEquals("bob", userGot.getUsername());
        assertEquals("zerty", userGot.getLastname());
        assertEquals(1000, userGot.getCreditPoints());

        userRepository.deleteUserById(userGot.getId());

    }


    @Test
    @DisplayName("Test findUserByUsername")
    public void findUserByUsernameTest(){
        Optional<User> user = userRepository.getUserByUsername("LineMartin");
        assertTrue(user.isPresent());
        assertEquals("LineMartin", user.get().getUsername());
        assertEquals("MARTIN", user.get().getLastname());

    }


    @Test
    public void findUserByEmailTest(){
        Optional<User> user = userRepository.getUserByEmail("sebastiaan.moedt2025@campus-eni.fr");
        assertTrue(user.isPresent());
        assertEquals(user.get().getUsername(), "SebastiaanMoedt");

    }


    @Test
    public void findUserByIdTest(){
        Optional<User> user = userRepository.getUserById(1);
        assertTrue(user.isPresent());
        assertEquals(user.get().getUsername(), "LineMartin");
    }

}
