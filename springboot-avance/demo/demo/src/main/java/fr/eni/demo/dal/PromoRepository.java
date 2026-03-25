package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
