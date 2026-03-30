package fr.eni.tp.bo.dal;

import fr.eni.tp.bo.Avis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvisRepository extends MongoRepository<Avis, String > {
}
