package fr.eni.tp.requetes;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp.bo.Avis;
import fr.eni.tp.bo.Client;
import fr.eni.tp.bo.vin.Bouteille;
import fr.eni.tp.dal.AvisRepository;
import fr.eni.tp.dal.BouteilleRepository;

@SpringBootTest
class DataPourRequetes {

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	AvisRepository avisRepository;

	void insertion_Bouteille_DB() {
		final List<Bouteille> listeBouteilles = new ArrayList<>();
		// Création de 3 Bouteille

		listeBouteilles.add(Bouteille
				.builder()
				.bouteilleId(18298)
				.regionId(3)
				.couleurId(1)
				.nom("Vin ENI Edition")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.bouteilleId(1298)
				.regionId(3)
				.couleurId(2)
				.nom("Vin ENI Service")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.bouteilleId(1999)
				.regionId(2)
				.couleurId(3)
				.nom("Vin ENI Ecole")
				.build());

        bouteilleRepository.saveAll(listeBouteilles);
	}

	void insertion_Avis_DB() {
		// Récupération depuis la base des Bouteille
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		// Liste de Client
		final List<Client> listeClients = new ArrayList<>();
		// Création de 3 Client
		listeClients.add(Client
				.builder()
				.pseudo("bobeponge@email.fr")
				.quantiteCommandee(11)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("patricketoile@email.fr")
				.quantiteCommandee(12)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("carlotentacule@email.fr")
				.quantiteCommandee(25)
				.build());

		// Ajout d'Avis par Client sur chaque Bouteille
		// Faire varier la note
		int note = 2;
		
		
		for (Client c : listeClients) {
			//Faire varier la date :
			LocalDateTime ldf = LocalDateTime.of(2023, 7, 13, 15, 28);
			//Attention, en base l'heure sera en GMT (Heure Française - 2)

			for (int i = 0; i < listeBouteilles.size(); i++) {
				final Bouteille b = listeBouteilles.get(i);

				// Faire varier la quantite du Client selon la note
				c.setQuantiteCommandee(c.getQuantiteCommandee() * note);
				final Avis avis = Avis
						.builder()
						.note(note)
						.commentaire("Commentaire (" + note + ")")
						.bouteille(b)
						.client(c)
						.date(ldf)
						.build();
				// Sauvegarde de Avis
				avisRepository.save(avis);
				// incrémenter la date
				ldf = ldf.plusDays(10);
			}
			// incrémenter la note
			note++;
		}
	}

	@Test
	void test_insertion_DB() {
		insertion_Bouteille_DB();
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		insertion_Avis_DB();
		final List<Avis> listeAvis = avisRepository.findAll();
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isEqualTo(9);
	}


}
