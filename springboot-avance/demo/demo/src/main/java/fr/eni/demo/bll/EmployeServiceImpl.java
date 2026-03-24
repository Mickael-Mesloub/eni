package fr.eni.demo.bll;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeServiceImpl implements EmployeService
{
    private EmployeRepository employeRepository;
    private AdresseRepository adresseRepository;

    @Override
    public void ajouter(Employe e) {
        // Vérifier qu'on reçoit bien un employé
        if(e ==  null){
            throw new RuntimeException("L'employé n'est pas renseigné");
        }

        // Vérifier que le nom a bien été renseigné
        if(e.getNom() == null || e.getNom().isBlank()) {
            throw new RuntimeException("Le nom de l'employé n'est pas renseigné");
        }
        // Vérifier que le prénom a bien été renseigné
        if(e.getPrenom() == null || e.getPrenom().isBlank()) {
            throw new RuntimeException("Le pré de l'employé n'est pas renseigné");
        }

        // Vérifier que l'immarticulation' a bien été renseigné
        if(e.getImmatriculation() == null || e.getImmatriculation().isBlank()) {
            throw new RuntimeException("L'immatriculation de l'employé n'est pas renseigné");
        }

        // Appel au repository pour trouver un employé par immatriculation

        // TODO replace
        //Optional<Employe> optionalEmploye = employeRepository.findByImmatriculation(e.getImmatriculation());

        // Vérifier si l'immatriculation existe. Si oui, lancer une exception
        // TODO replace
//        if(optionalEmploye.isPresent()) {
//            throw new RuntimeException("L'immatriculation existe déjà");
//        }

        // Créer l'employé
        employeRepository.save(e);
    }

    @Override
    @Transactional
    public void ajouter(Employe e, Adresse adresse) {
        ajouter(e);

        if(adresse == null) {
            throw new RuntimeException("L'adresse est obligatoire");
        }

        adresseRepository.save(adresse);
    }

    @Override
    public Employe lire(Integer id) {
        Optional<Employe> optionalEmploye =  employeRepository.findById(id);

        return optionalEmploye.orElse(null);
    }

    @Override
    public List<Employe> lireTousLesEmployes() {
        return employeRepository.findAll();
    }
}
