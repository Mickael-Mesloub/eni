package fr.eni.demo.association;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestOneToOne {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void test_create(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        employe.setAdresse(adresse);

        Employe employeDb = employeRepository.save(employe);

        log.info(employeDb.toString());

        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDb.getAdresse().getId()).isGreaterThan(0);
    }

    @Test
    public void test_delete(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        employe.setAdresse(adresse);

        Employe employeDb = employeRepository.save(employe);
        employeRepository.flush();
        entityManager.clear();

        log.info(employeDb.toString());

        Integer idEmploye = employe.getId();
        Integer idAdresse = adresse.getId();

        employeRepository.delete(employeDb);
        employeRepository.flush();
        entityManager.clear();

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);

        Assertions.assertThat(optionalEmploye).isEmpty();
        Assertions.assertThat(optionalAdresse).isEmpty();
    }

    @Test
    public void test_orphanRemoval(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue du bonheur")
                .codePostal("44230")
                .ville("St Sébastien sur Loire")
                .build();

        employe.setAdresse(adresse);

        Employe employeDb = employeRepository.save(employe);
        employeRepository.flush();
        entityManager.clear();

        log.info(employeDb.toString());

        Integer idEmploye = employe.getId();
        Integer idAdresse = adresse.getId();

        employe.setAdresse(null);

        employeRepository.delete(employeDb);
        employeRepository.flush();
        entityManager.clear();

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        Assertions.assertThat(optionalEmploye).isEmpty();

        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);
        Assertions.assertThat(optionalAdresse).isEmpty();
    }
}
