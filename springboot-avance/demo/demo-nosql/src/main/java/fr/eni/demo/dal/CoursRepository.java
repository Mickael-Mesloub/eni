package fr.eni.demo.dal;

import fr.eni.demo.bo.clecomposite.Cours;
import fr.eni.demo.bo.clecomposite.CoursId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursRepository extends MongoRepository<Cours, CoursId> {}
