package fr.eni.demo.demospring.bll;

import fr.eni.demo.demospring.bo.Formateur;
import fr.eni.demo.demospring.dal.FormateurDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("default")
@Service
public class FormateurServiceImpl implements FormateurService {

    private FormateurDAO formateurDAO;

    // constructeur avec injection d'une dépendance
    public FormateurServiceImpl(FormateurDAO formateurDAO) {
        this.formateurDAO = formateurDAO;
    }

    @Override
    public void add(Formateur formateur){
        formateurDAO.insert(formateur);
    }

    @Override
    public List<Formateur> getFormateurs(){
        return formateurDAO.findAll();
    }
}
