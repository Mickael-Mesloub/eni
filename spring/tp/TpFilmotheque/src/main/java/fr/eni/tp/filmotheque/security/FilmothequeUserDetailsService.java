package fr.eni.tp.filmotheque.security;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FilmothequeUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    // TODO : appeler repo, car on est déjà dans un service
    private MembreService membreService;

    public FilmothequeUserDetailsService(PasswordEncoder passwordEncoder, MembreService membreService) {
        this.passwordEncoder = passwordEncoder;
        this.membreService = membreService;
    }

    @Override
    // Cette méthode est appelée par Spring à chaque fois qu'un utilisateur essaye de se connecter
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Vérifier si l'utilisateur existe en BDD
        // Voir pour mettere un Optional (liste d'1 élément donc vérifier si isEmpty())
        Membre membre = membreService.consulterMembreParPseudo(username);

        if (!membre.getPseudo().equals(username)) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        User.UserBuilder userBuilder = User.withUsername(username)
                .password(passwordEncoder.encode(membre.getMotDePasse()))
                .roles(membre.isAdmin() ? "ADMIN" : "MEMBRE");

        return userBuilder.build();
    }

}
