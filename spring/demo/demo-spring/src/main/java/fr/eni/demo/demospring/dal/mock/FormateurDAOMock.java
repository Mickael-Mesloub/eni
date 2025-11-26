package fr.eni.demo.demospring.dal.mock;

import fr.eni.demo.demospring.bo.Formateur;
import fr.eni.demo.demospring.dal.FormateurDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FormateurDAOMock implements FormateurDAO {
    private List<Formateur> formateurs;

    public FormateurDAOMock() {
        formateurs = new ArrayList<>();
        formateurs.add(new Formateur("Baille", "Anne-Lise", "abaille@campus-eni.fr"));
        formateurs.add(new Formateur("Gobin", "Stéphane", "sgobin@campus-eni.fr"));
        formateurs.add(new Formateur("Trillard", "Julien", "jtrillard@campus-eni.fr"));
    }

    @Override
    public List<Formateur> findAll() {
        return formateurs;
    }

    @Override
    public void insert(Formateur formateur) {
        formateurs.add(formateur);
    }

    @Override
    public Formateur selectByEmail(String email) {
        return formateurs.stream()
                .filter(f -> f.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
