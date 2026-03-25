package fr.eni.cave.association;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import fr.eni.cave.dal.LignesPanierRepository;import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.client.Panier;
import fr.eni.cave.dal.PanierRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class TestOneToManyUni {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	PanierRepository panierRepository;

	@Autowired
	LignesPanierRepository lignesPanierRepository;

	private Panier panierEnDB() {
		final Panier panier = new Panier();
		final LignePanier lp = LignePanier
				.builder()
				.quantite(3)
				.prix(3 * 11.45f)
				.build();
		panier.getLignesPanier().add(lp);
		panier.setPrixTotal(lp.getPrix());

		entityManager.persist(panier);
		entityManager.flush();

		assertThat(panier.getId()).isGreaterThan(0);
		assertThat(panier.getId()).isGreaterThan(0);

		return panier;
	}

	@Test
	void test_save_nouvelleLigne_nouveauPanier(){
		Optional<Panier> optionalPanier = panierRepository.findById(panierEnDB().getId());

		assertThat(optionalPanier).isPresent();
		assertThat(optionalPanier.get().getLignesPanier()).isNotNull();
		assertThat(optionalPanier.get().getLignesPanier()).isNotEmpty();
		assertThat(optionalPanier.get().getLignesPanier().size()).isEqualTo(1);

		log.info(optionalPanier.get().toString());
		optionalPanier.get().getLignesPanier().forEach(lp -> log.info(lp.toString()));
	}

	@Test
	void test_save_nouvelleLigne_Panier(){
		LignePanier nouvelleLp = LignePanier
				.builder()
				.quantite(2)
				.prix(2 * 11.45f)
				.build();

		Panier nouveauPanier = panierEnDB();

		nouveauPanier.getLignesPanier().add(nouvelleLp);

		panierRepository.save(nouveauPanier);
		panierRepository.flush();
		entityManager.clear();

		Optional<Panier> optionalPanier = panierRepository.findById(nouveauPanier.getId());

		assertThat(optionalPanier).isPresent();
		assertThat(optionalPanier.get().getLignesPanier().size()).isEqualTo(2);

		log.info(optionalPanier.get().toString());
		optionalPanier.get().getLignesPanier().forEach(lp -> log.info(lp.toString()));
	}

	@Test
	void test_delete(){
		// TODO
		Panier panierASupprimer = panierEnDB();

		Integer idPanier = panierASupprimer.getId();
		List<LignePanier> lignesPanier = panierASupprimer.getLignesPanier();

		panierRepository.delete(panierASupprimer);
		panierRepository.flush();
		entityManager.clear();

		Optional<Panier> optionalPanier = panierRepository.findById(idPanier);

		assertThat(optionalPanier).isEmpty();

		// TODO: vérifier chaque ligne panier
	}

	@Test
	void test_orphanRemoval(){
		// TODO

	}
}
