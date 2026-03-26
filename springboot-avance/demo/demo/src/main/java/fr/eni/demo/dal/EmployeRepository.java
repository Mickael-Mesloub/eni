package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    Optional<Employe> findByImmatriculation(String immatriculation);

    @Query("SELECT e FROM Employe e WHERE e.email = :email")
    Optional<Employe> findByEmailJPQL(@Param("email") String email);
}
