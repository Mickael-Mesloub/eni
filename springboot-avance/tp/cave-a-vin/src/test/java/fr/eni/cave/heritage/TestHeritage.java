package fr.eni.cave.heritage;

import fr.eni.cave.bo.vin.Proprio;import fr.eni.cave.bo.Utilisateur;import fr.eni.cave.bo.client.Client;
import fr.eni.cave.dal.ClientRepository;
import fr.eni.cave.dal.ProprioRepository;
import fr.eni.cave.dal.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DataJpaTest
public class TestHeritage {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	ProprioRepository proprioRepository;

	@Autowired
	ClientRepository clientRepository;

	@BeforeEach
	public void initDB() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(Utilisateur
				.builder()
				.pseudo("harrisonford@email.fr")
				.password("IndianaJones3")
				.nom("Ford")
				.prenom("Harrison")
				.build());

		utilisateurs.add(Proprio
				.builder()
				.pseudo("georgelucas@email.fr")
				.password("Réalisateur&Producteur")
				.nom("Lucas")
				.prenom("George")
				.siret("12345678901234")
				.build());

		utilisateurs.add(Client
				.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build());

		// Contexte de la DB
		utilisateurs.forEach(e -> {
			entityManager.persist(e);
		});
	}

	@Test
	void test_findAllUtilisateurs() {
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

		Assertions.assertThat(utilisateurs).isNotNull();
		Assertions.assertThat(utilisateurs).isNotEmpty();
		Assertions.assertThat(utilisateurs.size()).isEqualTo(3);
		log.info(utilisateurs.toString());
	}

	@Test
	void test_findAllProprios() {
		List<Proprio> proprios = proprioRepository.findAll();

		Assertions.assertThat(proprios).isNotNull();
		Assertions.assertThat(proprios).isNotEmpty();
		Assertions.assertThat(proprios.size()).isEqualTo(1);
		log.info(proprios.toString());
	}

	@Test
	void test_findAllClients() {
		List<Client> clients = clientRepository.findAll();

		Assertions.assertThat(clients).isNotNull();
		Assertions.assertThat(clients).isNotEmpty();
		Assertions.assertThat(clients.size()).isEqualTo(1);
		log.info(clients.toString());
	}
}
