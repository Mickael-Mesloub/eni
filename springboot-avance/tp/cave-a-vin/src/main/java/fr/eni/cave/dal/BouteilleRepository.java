package fr.eni.cave.dal;

import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {
    List<Bouteille> findByRegion(Region region);
    List<Bouteille> findByCouleur(Couleur couleur);
    Bouteille findByNom(@Param("nom") String nom);
}
