package fr.eni.demo.bo;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class TestLombok {

    @Test
    public void test_Employe_tousLesAttributs(){
        Employe e1 = Employe.builder()
                .nom("NomTest")
                .prenom("PrénomTest")
                .email("emailTest@test.fr")
                .numPort("0603040506")
                .numDom("0203040506")
                .immatriculation("TEST01")
                .build();

        log.info(e1.toString());

        assertThat(e1.getNom()).isEqualTo("NomTest");
        assertThat(e1.getPrenom()).isEqualTo("PrénomTest");
    }

    @Test
    public void test_Employe_unAttribut(){
        Employe e1 = Employe.builder()
                .nom("NomTest")
                .build();

        log.info(e1.toString());

        assertThat(e1.getNom()).isEqualTo("NomTest");
    }
}
