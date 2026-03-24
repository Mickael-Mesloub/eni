package fr.eni.demo.dal;

import fr.eni.demo.bo.pk.Etudiant;
import fr.eni.demo.bo.pk.EtudiantPK;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEtudiantRepository {

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void test_save() {
        Etudiant etudiant = Etudiant.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj2025@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        etudiantRepository.save(etudiant);
        etudiantRepository.flush();
        entityManager.clear();

        EtudiantPK pk = EtudiantPK.builder()
                .email(etudiant.getEmail())
                .immatriculation(etudiant.getImmatriculation())
                .build();

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(pk);

        Assertions.assertThat(optionalEtudiant).isPresent();
    }
}
