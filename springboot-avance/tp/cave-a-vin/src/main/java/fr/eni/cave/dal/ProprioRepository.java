package fr.eni.cave.dal;

import fr.eni.cave.bo.vin.Proprio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprioRepository extends JpaRepository<Proprio, String> {
}
