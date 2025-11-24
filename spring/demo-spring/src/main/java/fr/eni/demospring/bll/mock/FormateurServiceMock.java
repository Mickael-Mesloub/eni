package fr.eni.demospring.bll.mock;

import fr.eni.demospring.bll.FormateurService;
import fr.eni.demospring.bo.Formateur;

import java.util.ArrayList;
import java.util.List;

public class FormateurServiceMock implements FormateurService {
    private List<Formateur> formateurs;

    public FormateurServiceMock() {
        formateurs = new ArrayList<>();
        formateurs.add(new Formateur("Baille", "Anne-Lise", "abaille@campus-eni.fr"));
        formateurs.add(new Formateur("Gobin", "Stéphane", "sgobin@campus-eni.fr"));
    }

    @Override
    public void add(Formateur formateur) {}

    @Override
    public List<Formateur> getFormateurs() {
        return formateurs;
    }
}
