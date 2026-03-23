package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

}
