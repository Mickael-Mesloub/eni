package fr.eni.demospring.dal.mock;

import fr.eni.demospring.bo.Formateur;
import fr.eni.demospring.dal.FormateurDAO;
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
}
