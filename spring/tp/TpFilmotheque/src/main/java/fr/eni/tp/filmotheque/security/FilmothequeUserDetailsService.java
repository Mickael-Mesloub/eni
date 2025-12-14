package fr.eni.tp.filmotheque.security;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FilmothequeUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final MembreRepository membreRepository;

    public FilmothequeUserDetailsService(PasswordEncoder passwordEncoder, MembreRepository membreRepository) {
        this.passwordEncoder = passwordEncoder;
        this.membreRepository = membreRepository;
    }

    @Override
    // Cette méthode est appelée par Spring à chaque fois qu'un utilisateur essaye de se connecter
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Vérifier si l'utilisateur existe en BDD
        Membre membre = membreRepository.findMembreByUsername(username);

        if (!membre.getPseudo().equals(username)) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        User.UserBuilder userBuilder = User.withUsername(username)
                .password(membre.getMotDePasse());

        if(membre.isAdmin()) {
            userBuilder.roles("ADMIN", "MEMBRE");
        } else {
            userBuilder.roles("MEMBRE");
        }

        return userBuilder.build();
    }

}
