package fr.eni.demo.association;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.bo.Stagiaire;
import fr.eni.demo.dal.AvisRepository;
import fr.eni.demo.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestAssoDocumentReference {
    @Autowired
    AvisRepository avisRepository;

    @Autowired
    FormateurRepository formateurRepository;

    @Test
    void _test01_insertAvisAvecStagiaireEtFormateur(){
        Stagiaire stagiaire = Stagiaire.builder()
                .immatriculation("JEANJ-01")
                .promotion("HCDA-100")
                .build();

        Formateur formateur = Formateur.builder()
                .email("geor.georges@campus-eni.fr")
                .nom("Georges")
                .prenom("Geor")
                .build();

        Avis avis = Avis
                .builder()
                .notePedagogique(15)
                .commentairePedagogique("Commentaire péda TestAssoDocumentReference")
                .noteCours(16)
                .commentaireCours("Commentaire cours TestAssoDocumentReference")
                .formateur(formateur)
                .build();

        Formateur formateurDB = formateurRepository.save(formateur);
        Avis avisDB = avisRepository.save(avis);

        Optional<Formateur> optionalFormateur = formateurRepository.findById(formateur.getEmail());

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        Assertions.assertThat(optionalFormateur).isPresent();
        Assertions.assertThat(optionalFormateur.get()).isEqualTo(formateur);

        log.info(avisDB.toString());
        log.info(optionalFormateur.get().toString());
    }
}
