package fr.eni.cave.association;

import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.LignesPanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestManyToOneLignePanierBouteille {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	LignesPanierRepository lignesPanierRepository;

	private Bouteille b1;
	private LignePanier lp1;
    @Autowired
    private BouteilleRepository bouteilleRepository;

	@BeforeEach
	public void initDB() {
		Integer quantite = 2;

		final Couleur blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		entityManager.persist(blanc);

		final Region paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		entityManager.persist(paysDeLaLoire);
		entityManager.flush();

		b1 = Bouteille
				.builder()
				.nom("DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build();
		entityManager.persist(b1);
		entityManager.flush();

		lp1 = LignePanier
				.builder()
				.quantite(quantite)
				.prix(quantite * b1.getPrix())
				.bouteille(b1)
				.build();

		entityManager.persist(lp1);
		entityManager.flush();
	}

	//TODO
	@Test
	void test_save() {
		Integer lpDBId = lp1.getId();
		entityManager.clear();

		Optional<LignePanier> optionalLignePanier = lignesPanierRepository.findById(lpDBId);

		Assertions.assertThat(optionalLignePanier).isPresent();
		Assertions.assertThat(optionalLignePanier.get().getId()).isEqualTo(lpDBId);

		log.info(optionalLignePanier.toString());
	}

	@Test
	void test_delete() {
		Integer lpDBId = lp1.getId();
		Integer b1DbId = b1.getId();

		entityManager.clear();

		lignesPanierRepository.delete(lp1);
		entityManager.flush();
		entityManager.clear();

		Optional<LignePanier> optionalLignePanier = lignesPanierRepository.findById(lpDBId);
		Optional<Bouteille> optionalBouteille = bouteilleRepository.findById(b1DbId);

		Assertions.assertThat(optionalLignePanier).isEmpty();
		Assertions.assertThat(optionalBouteille).isPresent();
		Assertions.assertThat(optionalBouteille.get().getId()).isEqualTo(b1DbId);







	}

}
