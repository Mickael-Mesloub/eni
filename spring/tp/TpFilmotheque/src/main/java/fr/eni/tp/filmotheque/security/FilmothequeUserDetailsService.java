package fr.eni.tp.filmotheque.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FilmothequeUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    public FilmothequeUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    // Cette méthode est appelée par Spring à chaque fois qu'une utilisateur essaye de se connecter
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Vérifier si l'utilisateur existe en BDD
        // Vérifier que les credentials sont bons
        if(!"bob".equals(username)) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        User.UserBuilder builder = User.withUsername(username)
                .password(passwordEncoder.encode("azerty"))
                .roles(("ADMIN"));

        return builder.build();
    }
}
