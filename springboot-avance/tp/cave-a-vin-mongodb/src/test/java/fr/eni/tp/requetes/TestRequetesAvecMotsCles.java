package fr.eni.tp.requetes;

import fr.eni.tp.bo.Avis;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestRequetesAvecMotsCles {
    @Autowired
    AvisRepository avisRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void test01_findAvisNoteLessThan3(){
        List<Avis> listeAvis = avisRepository.findByNoteLessThan(3);

        assertThat(listeAvis.size()).isEqualTo(3);
        log.info("Le nombre d'Avis avec une note < 3 est de : {}", listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test_02_findAvisNoteGreaterThanEqual3() {
        List<Avis> listeAvis = avisRepository.findByNoteGreaterThanEqual(3);
        assertThat(listeAvis.size()).isEqualTo(6);
        log.info("Le nombre d'Avis avec une note >= 2 est de : {}", listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test03_findAvisByBouteille() {
        Bouteille bouteille = bouteilleRepository.findAll().getFirst();

        List<Avis> listeAvis = avisRepository.findByBouteille(bouteille);
        assertThat(listeAvis.size()).isEqualTo(3);
        log.info("Le nombre d'Avis associés avec la bouteille id {} est de : {}", bouteille.getBouteilleId(), listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test04_findAvisByPseudoClient() {
        String pseudo = "bobeponge@email.fr";
        List<Avis> listeAvis = avisRepository.findByClient_Pseudo(pseudo);
        assertThat(listeAvis.size()).isEqualTo(3);
        log.info("Le nombre d'Avis associés avec le client {} est de : {}", pseudo, listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test05_findAvisByQuantiteCommandeeGreaterThan100() {
        Integer quantite = 100;
        List<Avis> listeAvis = avisRepository.findByClient_QuantiteCommandeeGreaterThan(100);
        assertThat(listeAvis.size()).isEqualTo(4);
        log.info("Le nombre d'Avis concernant une commande de plus de {} bouteilles est de : {}", quantite, listeAvis.size());
        log.info(listeAvis.toString());
    }

    @Test
    void test06_findAvisByDateBetween() {
        LocalDateTime from = LocalDateTime.of(2023, 7, 13, 10,28, 0);
        LocalDateTime to = LocalDateTime.of(2023, 8, 1, 10,28, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Avis> listeAvis = avisRepository.findByDateBetween(from, to);
        assertThat(listeAvis.size()).isEqualTo(6);
        log.info("Le nombre d'Avis concernant une commande passée entre le {} et le {} est de : {}", from.format(formatter), to.format(formatter), listeAvis.size());
        log.info(listeAvis.toString());
    }
}
