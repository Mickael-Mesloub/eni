package fr.eni.demo.association;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantENI;
import fr.eni.demo.bo.stagiaire.Promo;
import fr.eni.demo.dal.EtudiantENIRepository;
import fr.eni.demo.dal.PromoRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestOneToManyBidirectionnelle {

    @Autowired
    PromoRepository promoRepository;

    @Autowired
    EtudiantENIRepository etudiantENIRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void test_save() {
        // Créer la promo sans ID (la base s'en charge)
        // et sans étudiants (on va les ajouter après)
        Promo promo = Promo.builder()
                .nom("HCDA-100")
                .build();

        // Créer 3 étudiants qu'on ajoutera à la liste d'étudiants de la promo
        for (int i = 0; i < 3; i++) {
            EtudiantENI etudiantENI = EtudiantENI.builder()
                    .email("jean.jeanj-" + i + "2025@campus-eni.fr")
                    .immatriculation("POUET-" + i)
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Jeanj-" + i)
                    .prenom("Jean-" + i)
                    .numDom("014720000" + i)
                    .build();

            etudiantENI.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantENI(etudiantENI);

            // Ajout des étudiants à la liste
            promo.getEtudiants().add(etudiantENI);

            // Ajouter la promo à l'étudiant
            etudiantENI.setPromo(promo);
        }

        Promo promoDB = promoRepository.save(promo);
        promoRepository.flush();
        entityManager.clear();

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        List<EtudiantENI> etudiantsENI = etudiantENIRepository.findAll();
        etudiantsENI.forEach(e -> log.info(e.toString()));
        Assertions.assertThat(etudiantsENI.size()).isEqualTo(3);
    }

    @Test
    void test_delete() {
        // Créer la promo sans ID (la base s'en charge)
        // et sans étudiants (on va les ajouter après)
        Promo promo = Promo.builder()
                .nom("HCDA-100")
                .build();

        // Créer 3 étudiants qu'on ajoutera à la liste d'étudiants de la promo
        for (int i = 0; i < 3; i++) {
            EtudiantENI etudiantENI = EtudiantENI.builder()
                    .email("jean.jeanj-" + i + "2025@campus-eni.fr")
                    .immatriculation("POUET-" + i)
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Jeanj-" + i)
                    .prenom("Jean-" + i)
                    .numDom("014720000" + i)
                    .build();

            etudiantENI.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantENI(etudiantENI);

            // Ajouter la promo à l'étudiant
            etudiantENI.setPromo(promo);

            // Ajout des étudiants à la liste
            promo.getEtudiants().add(etudiantENI);
        }

        Promo promoDB = promoRepository.save(promo);
        promoRepository.flush();
        entityManager.clear();

        // Recherche de la promotion
        Optional<Promo> optionalPromo1 = promoRepository.findById(promoDB.getId());
        Assertions.assertThat(optionalPromo1).isPresent();

        // Recherche de tous les étudiants
        List<EtudiantENI> etudiantsENI1 = etudiantENIRepository.findAll();
        Assertions.assertThat(etudiantsENI1.size()).isEqualTo(3);

        etudiantsENI1.forEach(e -> log.info(e.toString()));

        // Sauvegarder l'id de la promo pour la récupérer plus tard
        Integer promoId = promoDB.getId();

        // Supprimer la promo
        promoRepository.delete(optionalPromo1.get());
        promoRepository.flush();
        // Clear le cache pour refaire une recherche directement en base
        entityManager.clear();

        // Tenter de récupérer la promo avec son id => Doit ne plus exister
        Optional<Promo> optionalPromo2 = promoRepository.findById(promoId);

        // Vérifier qu'elle n'existe plus
        Assertions.assertThat(optionalPromo2).isEmpty();

        // Mais on vérifie bien que les étudiants n'ont pas été supprimés en cascade
        List<EtudiantENI> etudiantsENI2 = etudiantENIRepository.findAll();
        Assertions.assertThat(etudiantsENI2.size()).isEqualTo(3);
    }
}
