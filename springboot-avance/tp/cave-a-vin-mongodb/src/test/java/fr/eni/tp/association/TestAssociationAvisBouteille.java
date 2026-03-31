package fr.eni.tp.association;

import fr.eni.tp.bo.Avis;
import fr.eni.tp.bo.Client;
import fr.eni.tp.bo.vin.Bouteille;
import fr.eni.tp.dal.AvisRepository;
import fr.eni.tp.dal.BouteilleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestAssociationAvisBouteille {

    @Autowired
    AvisRepository avisRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void test01_save_avis_bouteille() {
        // Récupération des Bouteille en base
        final List<Bouteille> listeBouteilleDB = bouteilleRepository.findAll();
        assertThat(listeBouteilleDB).isNotNull();
        assertThat(listeBouteilleDB).isNotEmpty();

        final Bouteille bouteilleDB = listeBouteilleDB.getFirst();

        final Client client = Client
                .builder()
                .pseudo("jean.jeanj@email.fr")
                .quantiteCommandee(16)
                .build();

        final Avis avis = Avis
                .builder()
                .note(5)
                .commentaire("Succulent !")
                .client(client)
                .build();

        // Association avec Bouteille
        avis.setBouteille(bouteilleDB);

        // Sauver
        final Avis avisDB = avisRepository.save(avis);

        // Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();

        // Vérifier que le Client n'est pas nul
        assertThat(avisDB.getClient()).isNotNull();
        assertThat(avisDB.getClient()).isEqualTo(client);

        // Vérifier que le Bouteille est complet
        assertThat(avisDB.getBouteille().getBouteilleId()).isNotNull();
        assertThat(avisDB.getBouteille()).isEqualTo(bouteilleDB);

        log.info(avisDB.toString());


        // recherche de la bouteille
        Optional<Avis> optionalAvis = avisRepository.findById(avisDB.getId());
        assertThat(optionalAvis).isPresent();

        log.info(optionalAvis.get().toString());


    }
}
