package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormateurRepository extends MongoRepository<Formateur, String> {
}
