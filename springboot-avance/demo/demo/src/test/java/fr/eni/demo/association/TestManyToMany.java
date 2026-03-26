package fr.eni.demo.association;

import fr.eni.demo.bo.formation.Cours;
import fr.eni.demo.bo.formation.Formateur;
import fr.eni.demo.dal.CoursRepository;
import fr.eni.demo.dal.FormateurRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestManyToMany {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private EntityManager entityManager;

    private final List<Cours> listeCours = new ArrayList<>();

    @BeforeEach
    void init_cours(){
        for (int i = 0 ; i < 3 ; i++){
            Cours cours = Cours.builder()
                    .titre("TITRE" + i)
                    .reference("REF" + i)
                    .filiere("FIL" + i)
                    .build();

            Cours coursBD = coursRepository.save(cours);
            listeCours.add(coursBD);
            log.info(coursBD.toString());

            Assertions.assertThat(coursBD.getId()).isGreaterThan(0);
        }
    }

    @Test
    void test_save(){
        Formateur formateur = Formateur.builder()
                .nom("Bernouille")
                .prenom("Bernard")
                .immatriculation("F-BerBer")
                .email("bernard.bernouille@campus-eni.fr")
                .build();

        formateur.setListeCours(listeCours);

        Formateur formateurDB = formateurRepository.save(formateur);

        log.info(formateurDB.toString());

        Assertions.assertThat(formateurDB.getId()).isGreaterThan(0);

        Assertions.assertThat(formateurDB.getListeCours().size()).isEqualTo(3);

    }

    @Test
    void test_delete(){
        Formateur formateur = Formateur.builder()
                .nom("Bernouille")
                .prenom("Bernard")
                .immatriculation("F-BerBer")
                .email("bernard.bernouille@campus-eni.fr")
                .build();

        formateur.setListeCours(listeCours);

        Formateur formateurDB = formateurRepository.save(formateur);
        formateurRepository.flush();
        entityManager.clear();

        log.info(formateurDB.toString());

        Assertions.assertThat(formateurDB.getId()).isGreaterThan(0);

        Assertions.assertThat(formateurDB.getListeCours().size()).isEqualTo(3);

        Integer idFormateur = formateurDB.getId();
        formateurRepository.deleteById(formateurDB.getId());
        formateurRepository.flush();
        entityManager.clear();

        Optional<Formateur> optionalFormateur = formateurRepository.findById(idFormateur);
        Assertions.assertThat(optionalFormateur).isEmpty();

        List<Cours> listeCours = coursRepository.findAll();
        Assertions.assertThat(listeCours.size()).isEqualTo(3);
    }
}