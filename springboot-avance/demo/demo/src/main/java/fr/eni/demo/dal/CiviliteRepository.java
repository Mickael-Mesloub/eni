package fr.eni.demo.dal;

import fr.eni.demo.bo.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiviliteRepository extends JpaRepository<Civilite, String> {
}
