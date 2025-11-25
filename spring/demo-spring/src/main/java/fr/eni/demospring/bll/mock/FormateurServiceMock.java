package fr.eni.demospring.bll.mock;

import fr.eni.demospring.bll.FormateurService;
import fr.eni.demospring.bo.Formateur;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Service
public class FormateurServiceMock implements FormateurService {
    private List<Formateur> formateurs;

    public FormateurServiceMock() {
        formateurs = new ArrayList<>();
        formateurs.add(new Formateur("Baille", "Anne-Lise", "abaille@campus-eni.fr"));
        formateurs.add(new Formateur("Gobin", "Stéphane", "sgobin@campus-eni.fr"));
    }

    @Override
    public void add(Formateur formateur) {
        formateurs.add(formateur);
    }

    @Override
    public List<Formateur> getFormateurs() {
        return formateurs;
    }
}
