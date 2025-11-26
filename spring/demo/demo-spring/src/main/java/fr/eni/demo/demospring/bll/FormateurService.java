package fr.eni.demo.demospring.bll;

import fr.eni.demo.demospring.bo.Formateur;

import java.util.List;

public interface FormateurService {
    void add(Formateur formateur);
    List<Formateur> getFormateurs();
    Formateur getFormateurByEmail(String email);
}
