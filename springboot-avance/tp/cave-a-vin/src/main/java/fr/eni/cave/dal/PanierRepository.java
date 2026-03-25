package fr.eni.cave.dal;

import fr.eni.cave.bo.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
