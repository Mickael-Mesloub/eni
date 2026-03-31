package fr.eni.demo.requetes;

import fr.eni.demo.bo.Avis;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.bo.Stagiaire;
import fr.eni.demo.bo.clecomposite.Cours;
import fr.eni.demo.bo.clecomposite.CoursId;
import fr.eni.demo.dal.AvisRepository;
import fr.eni.demo.dal.CoursRepository;
import fr.eni.demo.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestRequetes {

    @Autowired
    CoursRepository coursRepository;

    @Autowired
    FormateurRepository formateurRepository;

    @Autowired
    AvisRepository avisRepository;

    void insertion_Formateur_Cours_DB() {
        // Création de Formateur
        final List<Formateur> listeFormateurs = new ArrayList<>();
        listeFormateurs.add(Formateur
                .builder()
                .email("pmontembault@campus-eni.fr")
                .nom("MONTEMBAULT")
                .prenom("Philippe")
                .build());
        listeFormateurs.add(Formateur
                .builder()
                .email("fdelachesnais@campus-eni.frr")
                .nom("DELACHESNAIS")
                .prenom("Frédéric")
                .build());
        // Enregistrement en base
        listeFormateurs.forEach(formateur -> formateurRepository.save(formateur));

        // Création de Cours
        final List<Cours> listeCours = new ArrayList<>();
        listeCours.add(Cours
                .builder()
                .coursId(CoursId
                        .builder()
                        .reference("M030")
                        .filiere("Développement")
                        .build())
                .titre("Web Client")
                .duree(5)
                .build());
        listeCours.add(Cours
                .builder()
                .coursId(CoursId
                        .builder()
                        .reference("M070")
                        .filiere("Développement")
                        .build())
                .titre("POO")
                .duree(10)
                .build());
        // Enregistrement en base
        listeCours.forEach(cours -> coursRepository.save(cours));
    }


    void insertion_Avis_DB() {
        // Récupération depuis la base des Formateur et des Cours
        final List<Formateur> listeFormateurs = formateurRepository.findAll();
        assertThat(listeFormateurs).isNotNull();
        assertThat(listeFormateurs).isNotEmpty();
        assertThat(listeFormateurs.size()).isEqualTo(2);

        final List<Cours> listeCours = coursRepository.findAll();
        assertThat(listeCours).isNotNull();
        assertThat(listeCours).isNotEmpty();
        assertThat(listeCours.size()).isEqualTo(2);

        // Ajout d'Avis pour chaque Formateur avec chaque Cours
        for (int i = 0; i < listeFormateurs.size(); i++) {
            // Faire varier la note
            int note = 2;
            final Formateur f = listeFormateurs.get(i);

            for (int j = 0; j < listeCours.size(); j++) {
                final Cours c = listeCours.get(i);
                final Avis avis = Avis
                        .builder()
                        .notePedagogique(note)
                        .commentairePedagogique("Commentaire sur la pédagogie (" + note + ")")
                        .noteCours(note)
                        .commentaireCours("Commentaire du cours (" + note + ")")
                        .cours(c)
                        .formateur(f)
                        .stagiaire(Stagiaire
                                .builder()
                                .immatriculation("ENI_1253" + j)
                                .promotion("CDA1234" + j)
                                .build())
                        .build();
                // Sauvegarde de Avis
                avisRepository.save(avis);
                // incrémenter la note
                note++;
            }
        }
    }

    @BeforeEach
    void test00_insertion_DB() {
        avisRepository.deleteAll();
        coursRepository.deleteAll();
        formateurRepository.deleteAll();

        insertion_Formateur_Cours_DB();
        insertion_Avis_DB();
    }

    @Test
    void test01_findByNoteCours3(){
        List<Avis> listeAvis = avisRepository.findByNoteCours(3);

        assertThat(listeAvis.size()).isEqualTo(2);
        log.info(listeAvis.toString());
    }

    @Test
    void test02_findByNoteCoursGreaterThan2(){
        List<Avis> listeAvis = avisRepository.findByNoteCoursGreaterThan(2);

        assertThat(listeAvis.size()).isEqualTo(2);
        log.info(listeAvis.toString());
    }

    @Test
    void test03_findByNoteCoursLessThan4(){
        List<Avis> listeAvis = avisRepository.findByNoteCoursLessThan(4);

        assertThat(listeAvis.size()).isEqualTo(4);
        log.info(listeAvis.toString());
    }


    @Test
    void test04_findByStagiaire(){
        Stagiaire stagiaire = Stagiaire
                .builder()
                .immatriculation("ENI_1253" + 0)
                .promotion("CDA1234" + 0)
                .build();

        List<Avis> listeAvis = avisRepository.findByStagiaire(stagiaire);

        assertThat(listeAvis.size()).isEqualTo(2);
        log.info(listeAvis.toString());
    }

    @Test
    void test05_findByFormateur(){
        Formateur formateur = formateurRepository.findAll().getFirst();

        List<Avis> listeAvis = avisRepository.findByFormateur(formateur);

        assertThat(listeAvis.size()).isEqualTo(2);
        log.info(listeAvis.toString());
    }

    @Test
    void test06_findByCours(){
        Cours cours = coursRepository.findAll().getFirst();

        List<Avis> listeAvis = avisRepository.findByCours(cours);

        assertThat(listeAvis.size()).isEqualTo(2);
        log.info(listeAvis.toString());
    }
}
