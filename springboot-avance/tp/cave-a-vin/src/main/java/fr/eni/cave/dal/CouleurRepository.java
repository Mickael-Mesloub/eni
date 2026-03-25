package fr.eni.cave.dal;

import fr.eni.cave.bo.vin.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouleurRepository extends JpaRepository<Couleur, Integer> {
}
