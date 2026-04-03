package fr.eni.cave.dal;

import fr.eni.cave.bo.client.Client;
import fr.eni.cave.bo.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    @Query(value = "SELECT p.* FROM CAV_SHOPPING_CART p WHERE p.CLIENT_ID = :idClient AND p.ORDER_NUMBER IS NOT NULL", nativeQuery = true)
    List<Panier> findCommandesWithSQL(String idClient);
    List<Panier> findByClientAndNumCommandeNull(Client client);
}
