package fr.eni.tp.dal;

import fr.eni.tp.bo.Avis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvisRepository extends MongoRepository<Avis, String > {
}
