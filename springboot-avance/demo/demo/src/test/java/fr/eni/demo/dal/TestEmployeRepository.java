package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
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
public class TestEmployeRepository {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void test_read(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Employe employeDb = employeRepository.save(employe);
        entityManager.clear();

        log.info(employeDb.toString());

        Optional<Employe> optionalEmploye = employeRepository.findById(employeDb.getId());

        Assertions.assertThat(optionalEmploye.isPresent()).isTrue();
    }

    @Test
    public void test_findAll(){

        for(int i = 0; i < 3; i++) {
            Employe employe = Employe.builder()
                    .nom("Jeanj-" + i)
                    .prenom("Jean-" + i)
                    .email("jean.jeanj-" + i + "@campus-eni.fr")
                    .immatriculation("HCDA100-" + i).
                    numDom("014720000" + i)
                    .numPort("363" + i)
                    .build();
            employeRepository.save(employe);
        }

        employeRepository.flush();
        entityManager.clear();

        List<Employe> listeEmployes = employeRepository.findAll();

        Assertions.assertThat(listeEmployes.size()).isEqualTo(3);
    }

    @Test
    public void test_create(){
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Employe employeDb = employeRepository.save(employe);

        log.info(employeDb.toString());
        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);
    }

    @Test
    public void test_update(){
        // Créer un employé
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        // Le sauvegardé en base
        Employe employeDb = employeRepository.save(employe);

        // Logger pour vérifier qu'on a bien notre employé
        log.info(employeDb.toString());

        // Modifier son numéro de domicile
        employeDb.setNumDom("0147200059");

        // Sauvegarder les changements
        Employe employeDbMaj = employeRepository.save(employeDb);
        entityManager.flush();

        // Vérification des changements
        log.info(employeDbMaj.toString());

        // Test que les changements ont bien eu lieu
        Assertions.assertThat(employeDb.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDbMaj.getNumDom()).isEqualTo("0147200059");
    }

    @Test
    public void test_delete(){
        // Créer un employé
        Employe employe = Employe.builder()
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        // Le sauvegardé en base
        Employe employeDb = employeRepository.save(employe);

        // Logger pour vérifier qu'on a bien notre employé
        log.info(employeDb.toString());

        // Vider le cache (le contexte de persistence) pour pouvoir créer ET supprimer ensuite notre employé
        entityManager.clear();

        // Supprimer l'employé
        employeRepository.delete(employeDb);
        entityManager.flush();

        // Tenter de récupérer l'employé via son ID
        Optional<Employe> optionalEmploye = employeRepository.findById(employeDb.getId());

        // Test que l'employé a bien été supprimé'
        Assertions.assertThat(optionalEmploye.isEmpty()).isTrue();
    }
}
