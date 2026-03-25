package fr.eni.cave.dal;

import fr.eni.cave.bo.client.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignesPanierRepository extends JpaRepository<LignePanier, Integer> {
}
