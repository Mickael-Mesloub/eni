package fr.eni.tp.dal;

import fr.eni.tp.bo.Avis;
import fr.eni.tp.bo.vin.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "avis", path = "avis")
public interface AvisRepository extends MongoRepository<Avis, String > {
    List<Avis> findByNoteLessThan(int note);
    List<Avis> findByNoteGreaterThanEqual(int note);
    List<Avis> findByBouteille(Bouteille bouteille);
    List<Avis> findByClient_Pseudo(String pseudo);
    List<Avis> findByClient_QuantiteCommandeeGreaterThan(int quantite);
    List<Avis> findByDateBetween(LocalDateTime from, LocalDateTime to);
}
