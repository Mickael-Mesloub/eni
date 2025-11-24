package fr.eni.demospring.bll;

import fr.eni.demospring.bo.Formateur;

import java.util.List;

public interface FormateurService {
    void add(Formateur formateur);
    List<Formateur> getFormateurs();
}
