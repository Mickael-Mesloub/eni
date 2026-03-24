package fr.eni.demo.dal;

import fr.eni.demo.bo.pk2.Etudiant2;
import fr.eni.demo.bo.pk2.EtudiantPK2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Etudiant2Repository extends JpaRepository<Etudiant2, EtudiantPK2> {
}
