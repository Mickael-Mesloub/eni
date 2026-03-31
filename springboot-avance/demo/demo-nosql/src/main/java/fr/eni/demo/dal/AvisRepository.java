package fr.eni.demo.dal;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.bo.Stagiaire;
import fr.eni.demo.bo.clecomposite.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvisRepository extends MongoRepository<Avis, String> {
    List<Avis> findByNoteCours(int noteCours);
    List<Avis> findByNoteCoursGreaterThan(int noteCours);
    List<Avis> findByNoteCoursLessThan(int noteCours);

    List<Avis> findByStagiaire(Stagiaire stagiaire);

    List<Avis> findByFormateur(Formateur formateur);

    List<Avis> findByCours(Cours cours);
}
