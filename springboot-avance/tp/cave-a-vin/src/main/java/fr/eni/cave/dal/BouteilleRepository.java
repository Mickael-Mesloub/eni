package fr.eni.cave.dal;

import fr.eni.cave.bo.vin.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {
}
