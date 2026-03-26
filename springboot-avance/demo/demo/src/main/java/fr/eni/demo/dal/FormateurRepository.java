package fr.eni.demo.dal;

import fr.eni.demo.bo.formation.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Integer> {
}
