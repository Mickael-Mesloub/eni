package fr.eni.cave.association;

import fr.eni.cave.bo.Utilisateur;
import fr.eni.cave.bo.client.Client;
import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.client.Panier;
import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.ClientRepository;
import fr.eni.cave.dal.PanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestManyToOnePanierClient {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	PanierRepository panierRepository;

	@Autowired
	BouteilleRepository bouteilleRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@BeforeEach
	public void initDB() {
		final List<Couleur> couleurs = new ArrayList<>();
		couleurs.add(Couleur
				.builder()
				.nom("Blanc")
				.build());
		couleurs.add(Couleur
				.builder()
				.nom("Rouge")
				.build());

		couleurs.forEach(item -> {
			entityManager.persist(item);
			assertThat(item.getId()).isGreaterThan(0);
		});
		entityManager.flush();

		final List<Region> regions = new ArrayList<>();
		regions.add(Region
				.builder()
				.nom("Pays de la Loire")
				.build());

		regions.add(Region
				.builder()
				.nom("Grand Est")
				.build());

		regions.forEach(item -> {
			entityManager.persist(item);
			assertThat(item.getId()).isGreaterThan(0);
		});
		entityManager.flush();

		final List<Bouteille> bouteilles = new ArrayList<>();
		bouteilles.add(Bouteille
				.builder()
				.nom("DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(11.45f)
				.quantite(1298)
				.region(regions.get(0))
				.couleur(couleurs.get(0))
				.build());

		bouteilles.add(Bouteille
				.builder()
				.nom("DOMAINE ENI Service")
				.millesime("2015")
				.prix(23.95f)
				.quantite(2998)
				.region(regions.get(1))
				.couleur(couleurs.get(1))
				.build());

		bouteilles.forEach(item -> {
			entityManager.persist(item);
			assertThat(item.getId()).isGreaterThan(0);
		});
		entityManager.flush();
	}

//TODO

	private List<Panier> jeuDeDonnees() {
		final List<Bouteille> bouteilles = bouteilleRepository.findAll();
		assertThat(bouteilles).isNotNull();
		assertThat(bouteilles).isNotEmpty();
		assertThat(bouteilles.size()).isEqualTo(2);

		final List<Panier> paniers = new ArrayList<>();
		final Panier p1 = new Panier();
		final Panier p2 = new Panier();

		int qte1 = 3;
		final Bouteille b1 = bouteilles.getFirst();
		final LignePanier lp1 = LignePanier
				.builder()
				.bouteille(b1)
				.quantite(qte1)
				.prix(qte1 * b1.getPrix())
				.build();
		p1.getLignesPanier().add(lp1);
		p1.setPrixTotal(lp1.getPrix());
		paniers.add(p1);

		int qte2 = 10;
		final Bouteille b2 = bouteilles.get(1);
		final LignePanier lp2 = LignePanier
				.builder()
				.bouteille(b2)
				.quantite(qte2)
				.prix(qte2 * b2.getPrix())
				.build();
		p2.getLignesPanier().add(lp2);
		p2.setPrixTotal(lp2.getPrix());
		paniers.add(p2);

		return paniers;
	}

	@Test
	void test_save_1panier(){
		Panier panier1 = jeuDeDonnees().getFirst();

		Client client = Client.builder()
				.pseudo("Jeanjj")
				.password("azerty")
				.nom("Jeanj")
				.prenom("Jean")
				.build();

		panier1.setClient(client);

		String clientId = client.getPseudo();

		Panier panierDB = panierRepository.save(panier1);
		panierRepository.flush();
		entityManager.clear();

		Integer panierId = panierDB.getId();

		Optional<Panier> optionalPanier = panierRepository.findById(panierId);
		Optional<Client> optionalClient = clientRepository.findById(clientId);

		assertThat(optionalPanier).isPresent();
		assertThat(optionalPanier).isNotNull();
		assertThat(optionalPanier.get().getId()).isEqualTo(panierId);
		log.info(optionalPanier.toString());

		assertThat(optionalClient).isPresent();
		assertThat(optionalClient).isNotNull();
		assertThat(optionalClient.get().getPseudo()).isEqualTo(clientId);
		log.info(optionalClient.toString());
	}

	@Test
	void test_save_paniers_unClient() {
		List<Panier> paniers = jeuDeDonnees();

		Client client = Client.builder()
				.pseudo("Jeanjj")
				.password("azerty")
				.nom("Jeanj")
				.prenom("Jean")
				.build();

		paniers.forEach(p-> {
			p.setClient(client);
			entityManager.persist(p);
		});
		String clientId = client.getPseudo();

		Integer panier1Id = paniers.getFirst().getId();
		Integer panier2Id = paniers.get(1).getId();

		Optional<Panier> optionalPanier1 = panierRepository.findById(panier1Id);
		Optional<Panier> optionalPanier2 = panierRepository.findById(panier2Id);

		Optional<Client> optionalClient = clientRepository.findById(clientId);

		assertThat(optionalPanier1).isPresent();
		assertThat(optionalPanier1).isNotNull();
		assertThat(optionalPanier1.get().getId()).isEqualTo(panier1Id);
		log.info(optionalPanier1.toString());

		assertThat(optionalPanier2).isPresent();
		assertThat(optionalPanier2).isNotNull();
		assertThat(optionalPanier2.get().getId()).isEqualTo(panier2Id);
		log.info(optionalPanier2.toString());

		assertThat(optionalClient).isPresent();
		assertThat(optionalClient).isNotNull();
		assertThat(optionalClient.get().getPseudo()).isEqualTo(clientId);
		log.info(optionalClient.toString());
	}

	// TODO test_delete
}
