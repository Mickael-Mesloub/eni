package fr.eni.demo.bll;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;

import java.util.List;

public interface EmployeService {
    void ajouter(Employe e);
    Employe lire(Integer id);
    List<Employe> lireTousLesEmployes();
    void ajouter(Employe e, Adresse adresse);
}
