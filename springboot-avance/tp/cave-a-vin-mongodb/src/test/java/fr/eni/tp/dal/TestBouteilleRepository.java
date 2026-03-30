package fr.eni.tp.dal;

import fr.eni.tp.bo.vin.Bouteille;
import fr.eni.tp.bo.vin.BouteilleId;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class TestBouteilleRepository {
    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void test01_saveAndRead(){
        BouteilleId bouteilleId = BouteilleId.builder()
                .idBouteille(1)
                .idRegion(22).
                idCouleur(333)
                .build();

        Bouteille bouteille = Bouteille.builder()
                .bouteilleId(bouteilleId)
                .nom("Glouglou miamiam")
                .build();

        Bouteille bouteilleDB = bouteilleRepository.save(bouteille);

        Assertions.assertThat(bouteilleDB).isEqualTo(bouteille);
        log.info(bouteilleDB.toString());

        Optional<Bouteille> optionalBouteille = bouteilleRepository.findById(bouteilleId);

        Assertions.assertThat(optionalBouteille).isPresent();
        Assertions.assertThat(optionalBouteille.get().getBouteilleId()).isNotNull();
        Assertions.assertThat(optionalBouteille.get().getBouteilleId()).isEqualTo(bouteilleId);
        log.info(optionalBouteille.get().toString());
    }
}
