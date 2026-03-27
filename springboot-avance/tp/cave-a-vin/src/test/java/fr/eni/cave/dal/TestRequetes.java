package fr.eni.cave.dal;

import fr.eni.cave.bo.proprio.Proprio;
import fr.eni.cave.bo.proprio.Proprio;
import fr.eni.cave.bo.Utilisateur;
import fr.eni.cave.bo.client.Client;
import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
public class TestRequetes {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	BouteilleRepository bouteilleRepository;

	private Region paysDeLaLoire;
	private Couleur blanc;
	private List<Bouteille> bouteilles;
	private List<Utilisateur> utilisateurs = new ArrayList<>();


	@BeforeEach
	void initDB() {
		jeuDeDonneesBouteilles();
		jeuDeDonneesUtilisateur();
	}

	private void jeuDeDonneesBouteilles() {
		final Couleur rouge = Couleur
				.builder()
				.nom("Rouge")
				.build();

		blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		final Couleur rose = Couleur
				.builder()
				.nom("Rosé")
				.build();

		entityManager.persist(rouge);
		entityManager.persist(blanc);
		entityManager.persist(rose);
		entityManager.flush();

		final Region grandEst = Region
				.builder()
				.nom("Grand Est")
				.build();

		paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		final Region nouvelleAquitaine = Region
				.builder()
				.nom("Nouvelle-Aquitaine")
				.build();

		entityManager.persist(grandEst);
		entityManager.persist(paysDeLaLoire);
		entityManager.persist(nouvelleAquitaine);
		entityManager.flush();

		bouteilles = new ArrayList<>();
		bouteilles.add(Bouteille
				.builder()
				.nom("Blanc du DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rouge du DOMAINE ENI Ecole")
				.millesime("2018")
				.prix(11.45f)
				.quantite(987)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Blanc du DOMAINE ENI Service")
				.millesime("2022")
				.prix(34f)
				.petillant(true)
				.quantite(111)
				.region(grandEst)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rouge du DOMAINE ENI Service")
				.millesime("2012")
				.prix(8.15f)
				.quantite(344)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rosé du DOMAINE ENI")
				.millesime("2020")
				.prix(33f)
				.quantite(1987)
				.region(nouvelleAquitaine)
				.couleur(rose)
				.build());

		bouteilles.forEach(e -> {
			entityManager.persist(e);
			// Vérification de l'identifiant
			assertThat(e.getId()).isGreaterThan(0);
		});
		entityManager.flush();

	}

	private void jeuDeDonneesUtilisateur() {
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
				.siret("GEOLUC")
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
		entityManager.flush();
	}


	@Test
	void test_findBouteilleByRegionOK() {
		List<Bouteille> optionalBouteille = bouteilleRepository.findByRegion(paysDeLaLoire);

		Assertions.assertThat(optionalBouteille).isNotEmpty();

		log.info(optionalBouteille.toString());
	}

	@Test
	void test_findBouteilleByRegionInexistante() {
		Region regionInexistante = Region.builder()
				.id(99)
				.nom("Hyrule")
				.build();

		List<Bouteille> optionalBouteille = bouteilleRepository.findByRegion(regionInexistante);

		Assertions.assertThat(optionalBouteille).isEmpty();

		log.info(optionalBouteille.toString());
	}

	@Test
	void test_findBouteilleByCouleurOK() {
		List<Bouteille> optionalBouteille = bouteilleRepository.findByCouleur(blanc);

		Assertions.assertThat(optionalBouteille).isNotEmpty();
	}

	@Test
	void test_findBouteilleByCouleurInexistante() {
		Couleur couleurInexistante = Couleur.builder()
				.id(99)
				.nom("Glouglou")
				.build();

		List<Bouteille> optionalBouteille = bouteilleRepository.findByCouleur(couleurInexistante);

		Assertions.assertThat(optionalBouteille).isEmpty();
	}

	@Test
	void test_findUserByPseudo() {
		String userPseudo = "georgelucas@email.fr";
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByPseudo(userPseudo);

		Assertions.assertThat(optionalUtilisateur).isPresent();
		Assertions.assertThat(optionalUtilisateur.get().getPseudo()).isEqualTo(userPseudo);

		log.info(optionalUtilisateur.toString());
	}

	@Test
	void test_findUserByPseudoAndPassword() {
		String userPseudo = "georgelucas@email.fr";
		String userPassword = "Réalisateur&Producteur";

		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByPseudoAndPassword(userPseudo, userPassword);

		Assertions.assertThat(optionalUtilisateur).isPresent();
		Assertions.assertThat(optionalUtilisateur.get().getPseudo()).isEqualTo(userPseudo);

		log.info(optionalUtilisateur.toString());
	}

}
