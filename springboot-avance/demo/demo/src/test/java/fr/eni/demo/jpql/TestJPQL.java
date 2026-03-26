package fr.eni.demo.jpql;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestJPQL {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void insert_employe() {
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Employe employeDb = employeRepository.save(employe);

        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);
        log.info(employeDb.toString());
    }

    @Test
    public void test_findEmployeByEmailJPQL(){
        Optional<Employe> employe = employeRepository.findByEmailJPQL("jean.jeanj@campus-eni.fr");

        Assertions.assertThat(employe).isPresent();
        Assertions.assertThat(employe.get().getId()).isGreaterThan(0);
        log.info(employe.toString());
    }

}
