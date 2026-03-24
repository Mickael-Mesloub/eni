package fr.eni.demo.dal;

import fr.eni.demo.bo.pk2.Etudiant2;
import fr.eni.demo.bo.pk2.EtudiantPK2;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEtudiant2Repository {

    @Autowired
    Etudiant2Repository etudiant2Repository;

    @Autowired
    EntityManager entityManager;

    @Test
    void test_save() {
        EtudiantPK2 pk = EtudiantPK2.builder()
                .email("jean.jeanj2025@campus-eni.fr")
                .immatriculation("HCDA100-1")
                .build();

        Etudiant2 etudiant = Etudiant2.builder()
                .pk(pk)
                .nom("Jeanj")
                .prenom("Jean")
                .numDom("0147200001")
                .numPort("3630")
                .build();

        etudiant2Repository.save(etudiant);
        etudiant2Repository.flush();
        entityManager.clear();

        Optional<Etudiant2> optionalEtudiant = etudiant2Repository.findById(pk);

        Assertions.assertThat(optionalEtudiant).isPresent();
    }
}
