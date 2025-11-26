package fr.eni.demo.demospring.dal;

import fr.eni.demo.demospring.bo.Formateur;

import java.util.List;

public interface FormateurDAO {
    List<Formateur> findAll();
    void insert(Formateur formateur);
    Formateur selectByEmail(String email);
}
