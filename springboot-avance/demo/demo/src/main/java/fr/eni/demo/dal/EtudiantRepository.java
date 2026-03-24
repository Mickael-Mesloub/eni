package fr.eni.demo.dal;

import fr.eni.demo.bo.pk.Etudiant;
import fr.eni.demo.bo.pk.EtudiantPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, EtudiantPK> {
}
