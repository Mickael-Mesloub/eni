package fr.eni.demo.association;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.clecomposite.Cours;
import fr.eni.demo.bo.clecomposite.CoursId;
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
public class TestAssoDbRef {
    @Autowired
    AvisRepository avisRepository;

    @Test
    void _test01_insertAvisAvecCours(){
        CoursId coursId = CoursId.builder()
                .reference("COURS_DIGI")
                .filiere("DEV_DIGI")
                .build();

        Cours cours = Cours.builder()
                .coursId(coursId)
                .titre("Développement digital avec les doigts digitaux doigteux")
                .duree(55)
                .build();

        Avis avis = Avis
                .builder()
                .notePedagogique(15)
                .commentairePedagogique("Commentaire péda TestAssoDbRef")
                .noteCours(16)
                .commentaireCours("Commentaire cours TestAssoDbRef")
                .cours(cours)
                .build();

        Avis avisDB = avisRepository.save(avis);

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        log.info(avisDB.toString());
    }
}
