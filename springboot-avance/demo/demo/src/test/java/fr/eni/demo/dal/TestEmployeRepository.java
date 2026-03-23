package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEmployeRepository {
    @Autowired
    private EmployeRepository employeRepository;

    @Test
    public void test_save(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Employe employeDb = employeRepository.save(employe);

        log.info(employeDb.toString());
        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);

    }

    @Test
    public void test_read(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Employe employeDb = employeRepository.save(employe);
        employeRepository.flush();

        log.info(employeDb.toString());

        Optional<Employe> optionalEmploye = employeRepository.findById(employeDb.getId());

        Assertions.assertThat(optionalEmploye.isPresent()).isTrue();
    }
}
