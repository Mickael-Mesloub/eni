package fr.eni.demo.dal;

import fr.eni.demo.bo.clecomposite.Cours;
import fr.eni.demo.bo.clecomposite.CoursId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cours", path = "cours")
public interface CoursRepository extends MongoRepository<Cours, CoursId> {}
