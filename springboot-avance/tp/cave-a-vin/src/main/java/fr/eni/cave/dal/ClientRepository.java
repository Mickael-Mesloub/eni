package fr.eni.cave.dal;

import fr.eni.cave.bo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
