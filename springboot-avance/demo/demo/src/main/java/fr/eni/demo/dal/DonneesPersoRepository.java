package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonneesPersoRepository extends JpaRepository<DonneesPerso, Integer> {
}
