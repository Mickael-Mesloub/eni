package fr.eni.tp.dal;

import fr.eni.tp.bo.Avis;
import fr.eni.tp.bo.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
// Exécution des méthodes de test par ordre alphabétique
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestAvisRepository {
    @Autowired
    AvisRepository avisRepository;

    @Test
    void test01_save(){
        Avis avis = Avis.builder()
                .note(5)
                .commentaire("Super top")
                .date(LocalDateTime.now())
                .build();

        Avis avisDB = avisRepository.save(avis);

        Assertions.assertThat(avisDB.getId()).isNotBlank();
        log.info(avisDB.toString());

    }

    @Test
    void test02_findAll() {
        List<Avis> listeAvis = avisRepository.findAll();

        Assertions.assertThat(listeAvis.size()).isGreaterThan(0);
        log.info(listeAvis.toString());
    }
}
