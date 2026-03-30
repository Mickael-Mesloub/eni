package fr.eni.demo.bo;

import fr.eni.demo.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
// Exécution des méthodes de test par ordre alphabétique
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestFormateur {
    @Autowired
    FormateurRepository formateurRepository;

    @Test
    void test01_insertFormateurOK() {
        Formateur formateur = Formateur.builder()
                .email("gregory.michaud@campus-eni.fr")
                .nom("Michaud")
                .prenom("Grégory")
                .build();

        Formateur formateurDB = formateurRepository.save(formateur);

        Assertions.assertThat(formateurDB.getEmail()).isNotBlank();
        Assertions.assertThat(formateurDB).isEqualTo(formateur);
        log.info(formateurDB.toString());
    }

    @Test
    void test02_insertFormateurPasOK() {
        Formateur formateur = Formateur.builder()
                .email("gregory.michaud2@campus-eni.fr")
                .nom("Michaud")
                .prenom("Georges")
                .build();

        org.junit.jupiter.api.Assertions.assertThrows(
                DuplicateKeyException.class,
                () -> formateurRepository.save(formateur)
        );
    }
}
