package fr.eni.demo.association;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.Stagiaire;
import fr.eni.demo.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestAssoComposition {
    @Autowired
    AvisRepository avisRepository;

    @Test
    void _test01_insertAvisAvecStagiaire(){
        Avis avis = Avis
                .builder()
                .notePedagogique(15)
                .commentairePedagogique("Commentaire péda testAssoCompo")
                .noteCours(16)
                .commentaireCours("Commentaire cours testAssoCompo")
                .build();

        Stagiaire stagiaire = Stagiaire.builder()
                .immatriculation("JEANJ-01")
                .promotion("HCDA-100")
                .build();

        avis.setStagiaire(stagiaire);

        Avis avisDB = avisRepository.save(avis);

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        log.info(avisDB.toString());
    }
}
