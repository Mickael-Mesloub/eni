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
public class EmployeServiceImpl implements EmployeService {

    private EmployeRepository employeRepository;
    private AdresseRepository adresseRepository;

    @Override
    public void ajouter(Employe e) {

        if(e == null){
            throw new RuntimeException("L'employé n'est pas renseigné");
        }
        if(e.getNom() == null || e.getNom().isBlank()){
            throw new RuntimeException("Le nom de l'mployé doit être renseigné");
        }

        if(e.getPrenom() == null || e.getPrenom().isBlank()){
            throw new RuntimeException("Le prénom de l'employé doit être renseigné");
        }

        if(e.getImmatriculation() == null || e.getImmatriculation().isBlank()){
            throw new RuntimeException("L'immatriculation doit être renseignée");
        }

        Optional<Employe> employe = employeRepository.findByImmatriculation(e.getImmatriculation());
        if(employe.isPresent()){
            throw new RuntimeException("L'immatriculation existe déjà");
        }

        try{
            employeRepository.save(e);
        }catch (RuntimeException ex){
            throw new RuntimeException("Création de l'employe impossible : " + e);
        }

    }

    @Override
    public Employe lire(Integer id) {
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        if(optionalEmploye.isPresent()){
            return optionalEmploye.get();
        }
        throw new RuntimeException("L'employé n'existe pas.");
    }

    @Override
    public List<Employe> lireTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Transactional
    @Override
    public void ajouter(Employe e, Adresse adresse) {
        ajouter(e);

        if(adresse == null){
            throw new RuntimeException("L'adresse est obligatoire");
        }
        adresseRepository.save(adresse);
    }
}