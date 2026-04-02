package fr.eni.tp.dal;

import fr.eni.tp.bo.Avis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "avis", path = "avis")
public interface AvisRepository extends MongoRepository<Avis, String > {
    List<Avis> findByNoteLessThan(int note);
    List<Avis> findByNoteGreaterThanEqual(int note);
    List<Avis> findByBouteilleBouteilleId(Integer bouteilleId);
    List<Avis> findByClientPseudo(String pseudo);
    List<Avis> findByClientQuantiteCommandeeGreaterThan(int quantite);

    List<Avis> findByDateBetween(LocalDateTime from, LocalDateTime to);
}
