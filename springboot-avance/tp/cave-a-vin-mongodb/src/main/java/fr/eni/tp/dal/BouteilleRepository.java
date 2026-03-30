package fr.eni.tp.dal;

import fr.eni.tp.bo.vin.Bouteille;
import fr.eni.tp.bo.vin.BouteilleId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BouteilleRepository extends MongoRepository<Bouteille, BouteilleId> {
}
