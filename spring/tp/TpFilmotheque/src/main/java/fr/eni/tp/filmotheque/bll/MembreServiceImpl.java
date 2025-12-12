package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.stereotype.Service;

@Service
public class MembreServiceImpl implements MembreService {
    MembreRepository membreRepository;

    public MembreServiceImpl(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @Override
    public Membre consulterMembreParPseudo(String pseudo) {
        return membreRepository.findMembreByUsername(pseudo);
    }
}
