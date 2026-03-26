package fr.eni.demo.dal;

import fr.eni.demo.bo.formation.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Integer> {
}
