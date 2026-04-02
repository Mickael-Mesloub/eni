package fr.eni.demo.dal;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.bo.Stagiaire;
import fr.eni.demo.bo.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "avis", path = "avis")
public interface AvisRepository extends MongoRepository<Avis, String> {
    List<Avis> findByNoteCours(int noteCours);
    List<Avis> findByNoteCoursGreaterThan(int noteCours);
    List<Avis> findByNoteCoursLessThan(int noteCours);

    List<Avis> findByStagiaire(Stagiaire stagiaire);
    List<Avis> findByStagiaireImmatriculation(String immatriculation);

    List<Avis> findByFormateur(Formateur formateur);
    List<Avis> findByFormateurEmail(String email);

    List<Avis> findByCours(Cours cours);
}
