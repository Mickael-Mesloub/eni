package fr.eni.demo.association;

import fr.eni.demo.bo.Civilite;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.CiviliteRepository;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
public class TestManyToOne {

    @Autowired
    CiviliteRepository civiliteRepository;

    @Autowired
    EmployeRepository employeRepository;

    @Test
    public void test_create(){
        Civilite madame = Civilite.builder()
                .cle("MME")
                .libelle("Madame")
                .build();

        Civilite madameDB = civiliteRepository.save(madame);

        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();


        employe.setCivilite(madameDB);

        Employe employeDb = employeRepository.save(employe);

        log.info(employeDb.toString());
        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);
    }
}
