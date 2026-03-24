package fr.eni.demo.association;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantENI;
import fr.eni.demo.dal.DonneesPersoRepository;
import fr.eni.demo.dal.EtudiantENIRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
public class TestOneToOneBidirectionnelle {
    @Autowired
    private EtudiantENIRepository etudiantENIRepository;

    @Autowired
    private DonneesPersoRepository donneesPersoRepository;

    @Test
    void test_save() {
        EtudiantENI etudiantENI = EtudiantENI.builder()
                .email("jean.jeanj2025@campus-eni.fr")
                .immatriculation("HDCA-100-1")
                .build();

        DonneesPerso donneesPerso = DonneesPerso.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .numDom("0147200001")
                .build();

        etudiantENI.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantENI(etudiantENI);

        EtudiantENI etudiantEniDB = etudiantENIRepository.save(etudiantENI);

        log.info(etudiantEniDB.toString());

        Assertions.assertThat(etudiantEniDB.getDonneesPerso().getId()).isGreaterThan(0);
    }
}
