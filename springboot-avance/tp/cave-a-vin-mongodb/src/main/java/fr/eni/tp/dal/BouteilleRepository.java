package fr.eni.tp.dal;

import fr.eni.tp.bo.vin.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BouteilleRepository extends MongoRepository<Bouteille, Integer> {
}
