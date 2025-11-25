package fr.eni.demospring.dal;

import fr.eni.demospring.bo.Formateur;

import java.util.List;

public interface FormateurDAO {
    List<Formateur> findAll();

    void insert(Formateur formateur);
}
