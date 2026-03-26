package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class TestEmployeService {
    @Autowired
    private EmployeService employeService;

    @MockitoBean
    private EmployeRepository employeRepository;

    @MockitoBean
    private AdresseRepository adresseRepository;

    @Test
    void test_lireTousLesEmployes() {
        List<Employe> employes = new ArrayList<>();
        employes.add(Employe.builder()
                .id(1)
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build()
        );

        employes.add(Employe.builder()
                .id(2)
                .nom("Johnj")
                .prenom("John")
                .email("john.johnj@campus-eni.fr")
                .immatriculation("HCDA100-2")
                .numDom("0147200002")
                .numPort("3631")
                .build()
        );

        Mockito.when(employeRepository.findAll()).thenReturn(employes);

        List<Employe> listeEmployes = employeService.lireTousLesEmployes();

        assertThat(listeEmployes.size()).isEqualTo(2);

    }

    @Test
    void test_ajouterEmployeNull() {
        assertThrows(
                RuntimeException.class,
                () -> employeService.ajouter(null)
        );
    }

    @Test
    void test_ajouterEmployeImmatriculationExisteDeja() {
        Employe employe = Employe.builder()
                .id(1)
                .nom("Jeanj")
                .prenom("Jean")
                .email("jean.jeanj@campus-eni.fr")
                .immatriculation("HCDA100-1").
                numDom("0147200001")
                .numPort("3630")
                .build();

        Optional<Employe> optionalEmploye = Optional.of(employe);

        Mockito.when(employeRepository.findByImmatriculation("HCDA100-1")).thenReturn(optionalEmploye);

        assertThrows(
                RuntimeException.class,
                () -> employeService.ajouter(employe)
        );
    }
}
