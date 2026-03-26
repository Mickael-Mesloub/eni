package fr.eni.cave.dal;

import fr.eni.cave.bo.Adresse;
import fr.eni.cave.bo.client.Client;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestOneToOneAdresse {
    @Autowired
    private AdresseRepository adresseRepository;

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

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        client.setAdresse(adresse);

        Client clientDB = clientRepository.save(client);
        clientRepository.flush();

        String clientId = clientDB.getPseudo();
        Integer adresseId = clientDB.getAdresse().getId();

        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        Optional<Adresse> optionalAdresse = adresseRepository.findById(adresseId);

        log.info(optionalClient.get().toString());
        log.info(optionalAdresse.get().toString());

        Assertions.assertThat(optionalClient).isPresent();
        Assertions.assertThat(optionalAdresse).isPresent();
    }

    @Test
    void test_delete() {
        Client client = Client.builder()
                .pseudo("Jeanjj")
                .password("azerty")
                .nom("Jeanj")
                .prenom("Jean")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        client.setAdresse(adresse);

        Client clientDB = clientRepository.save(client);
        clientRepository.flush();

        String clientId = clientDB.getPseudo();
        Integer adresseId = clientDB.getAdresse().getId();

        clientRepository.delete(client);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        Optional<Adresse> optionalAdresse = adresseRepository.findById(adresseId);

        Assertions.assertThat(optionalClient).isEmpty();
        Assertions.assertThat(optionalAdresse).isEmpty();
    }

    @Test
    void test_orphanRemoval() {
        Client client = Client.builder()
                .pseudo("Jeanjj")
                .password("azerty")
                .nom("Jeanj")
                .prenom("Jean")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        client.setAdresse(adresse);

        Client clientDB = clientRepository.save(client);
        clientRepository.flush();

        String clientId = clientDB.getPseudo();
        Integer adresseId = clientDB.getAdresse().getId();

        clientDB.setAdresse(null);

        clientRepository.delete(client);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        Optional<Adresse> optionalAdresse = adresseRepository.findById(adresseId);

        Assertions.assertThat(optionalClient).isEmpty();
        Assertions.assertThat(optionalAdresse).isEmpty();
    }
}
