package fr.eni.cave.dal;

import fr.eni.cave.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByPseudo(String pseudo);
    Optional<Utilisateur> findByPseudoAndPassword(String pseudo, String password);

}
