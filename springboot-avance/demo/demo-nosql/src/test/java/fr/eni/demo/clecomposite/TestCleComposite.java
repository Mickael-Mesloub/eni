package fr.eni.demo.clecomposite;

import fr.eni.demo.bo.clecomposite.Cours;
import fr.eni.demo.bo.clecomposite.CoursId;
import fr.eni.demo.dal.CoursRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCleComposite {
    @Autowired
    CoursRepository coursRepository;

    @Test
    void test01_insertCours(){
        CoursId coursId = CoursId.builder()
                .reference("DEV")
                .filiere("REF01")
                .build();

        Cours cours = Cours.builder()
                .coursId(coursId)
                .titre("Cours test")
                .duree(60)
                .build();

        Cours coursDB = coursRepository.save(cours);

        Assertions.assertThat(coursDB).isEqualTo(cours);
        log.info(coursDB.toString());
    }

    @Test
    void test02_(){

    }
}
