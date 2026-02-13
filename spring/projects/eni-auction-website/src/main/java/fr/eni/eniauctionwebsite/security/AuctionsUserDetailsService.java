package fr.eni.eniauctionwebsite.security;

import fr.eni.eniauctionwebsite.dal.UserRepository;
import fr.eni.eniauctionwebsite.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionsUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuctionsUserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<fr.eni.eniauctionwebsite.bo.User> user;

        if (username.contains("@")) {
            user = userRepository.getUserByEmail(username);

            if (user.isEmpty()) {
                throw new UserNotFoundException("username " + username);
            }
        } else {
            user = userRepository.getUserByUsername(username);

            if (user.isEmpty()) {
                throw new UserNotFoundException("email " + username);
            }
        }

        User.UserBuilder builder = User.withUsername(username)
                .password(user.get().getPassword());

        if (user.get().isAdmin()) {
            builder.roles("ADMIN", "USER");
        } else {
            builder.roles("USER ");
        }

        return builder.build();
    }
}