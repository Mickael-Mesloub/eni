package fr.eni.cave.association;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.CouleurRepository;
import fr.eni.cave.dal.RegionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class TestManyToOne {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	CouleurRepository couleurRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	Couleur rouge;
	Couleur blanc;
	Couleur rose;
	
	Region grandEst;
	Region paysDeLaLoire;
	Region nouvelleAquitaine;

	@BeforeEach
	public void initDB() {
		rouge = Couleur
				.builder()
				.nom("Rouge")
				.build();
		
		blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();
				
		rose = Couleur
				.builder()
				.nom("Rosé")
				.build();
				
		couleurRepository.save(rouge);
		couleurRepository.save(blanc);
		couleurRepository.save(rose);
				
		grandEst = 
				Region
				.builder()
				.nom("Grand Est")
				.build();
		
		paysDeLaLoire = 
				Region
				.builder()
				.nom("Pays de la Loire")
				.build();
		
		nouvelleAquitaine = 
				Region
				.builder()
				.nom("Nouvelle Aquitaine")
				.build();
		
		regionRepository.save(grandEst);
		regionRepository.save(paysDeLaLoire);
		regionRepository.save(nouvelleAquitaine);
	}

	//TODO

	private List<Bouteille> jeuDeDonnees() {
		List<Bouteille> bouteilles = new ArrayList<>();
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
		return bouteilles;
	}

	@Test
	void test_save(){
		List<Bouteille> bouteilles = jeuDeDonnees();

		List<Bouteille> bouteillesEnBase = bouteilleRepository.saveAll(bouteilles);
		bouteilleRepository.flush();
		entityManager.clear();

		assertThat(bouteillesEnBase).isNotNull();
		assertThat(bouteillesEnBase).isNotEmpty();
		assertThat(bouteillesEnBase.size()).isEqualTo(5);
		bouteillesEnBase.forEach(bouteille -> log.info(bouteille.toString()));

		List<Couleur> couleursEnBase = couleurRepository.findAll();

		assertThat(couleursEnBase).isNotNull();
		assertThat(couleursEnBase).isNotEmpty();
		assertThat(couleursEnBase.size()).isEqualTo(3);
		couleursEnBase.forEach(couleur -> log.info(couleur.toString()));

		List<Region> regionsEnBase = regionRepository.findAll();

		assertThat(regionsEnBase).isNotNull();
		assertThat(regionsEnBase).isNotEmpty();
		assertThat(regionsEnBase.size()).isEqualTo(3);
		regionsEnBase.forEach(region -> log.info(region.toString()));
	}

	@Test
	void test_save_bouteilles_regions_couleurs(){
		// TODO
	}

	@Test
	void test_delete() {
		// TODO
	}
}
