package fr.eni.demo.bll;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestTransaction {

    @Autowired
    private EmployeService employeService;

    @Test
    void test_ajouterEmployeOK() {
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        employeService.ajouter(employe);

        log.info(employe.toString());

        Assertions.assertThat(employe.getId()).isGreaterThan(0);
    }

    @Test
    void test_ajouterEmployeAvecAdresse() {
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj-2@campus-eni.fr")
                .immatriculation("HCDA100-2").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("Rue des coquelicots")
                .codePostal("44200")
                .ville("Nantes")
                .build();

        employeService.ajouter(employe, adresse);

        log.info(employe.toString());
        log.info(adresse.toString());

        Assertions.assertThat(employe.getId()).isGreaterThan(0);
        Assertions.assertThat(adresse.getId()).isGreaterThan(0);
    }
}
