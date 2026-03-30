package fr.eni.demo.bo;

import fr.eni.demo.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
// Exécution des méthodes de test par ordre alphabétique
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestAvis {
    @Autowired
    AvisRepository avisRepository;

    @Test
    void test01_insererAvisSansCommentaireCours() {
        Avis avis = Avis
                .builder()
                .notePedagogique(4)
                .commentairePedagogique("Commentaire péda")
                .noteCours(3)
                .build();

        Avis avisDB = avisRepository.save(avis);

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        log.info(avisDB.toString());
    }

    @Test
    void test02_insererAviscComplet() {
        Avis avis = Avis
                .builder()
                .notePedagogique(4)
                .commentairePedagogique("Commentaire péda")
                .noteCours(3)
                .commentaireCours("Commentaire cours")
                .build();

        Avis avisDB = avisRepository.save(avis);

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        log.info(avisDB.toString());
    }

    @Test
    void test03_findAll() {
        List<Avis> listeAvis = avisRepository.findAll();

        log.info(listeAvis.toString());

        Assertions.assertThat(listeAvis.size()).isGreaterThan(0);

    }
}
