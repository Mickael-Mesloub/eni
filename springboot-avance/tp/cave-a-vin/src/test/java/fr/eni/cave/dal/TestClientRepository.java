package fr.eni.cave.dal;

import fr.eni.cave.bo.Client;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_create() {
        Client client = Client.builder()
                .pseudo("Jeanjj")
                .password("azerty")
                .nom("Jeanj")
                .prenom("Jean")
                .build();

        clientRepository.save(client);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById("Jeanjj");

        log.info(optionalClient.get().toString());

        Assertions.assertThat(optionalClient).isPresent();
    }

    @Test
    void test_delete() {
        Client client = Client.builder()
                .pseudo("Jeanjj")
                .password("azerty")
                .nom("Jeanj")
                .prenom("Jean")
                .build();

        clientRepository.save(client);
        clientRepository.flush();
        entityManager.clear();

        clientRepository.delete(client);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById("Jeanjj");

        Assertions.assertThat(optionalClient).isEmpty();
    }

}
