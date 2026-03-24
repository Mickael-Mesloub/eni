package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.EtudiantENI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantENIRepository extends JpaRepository<EtudiantENI, String> {
}
