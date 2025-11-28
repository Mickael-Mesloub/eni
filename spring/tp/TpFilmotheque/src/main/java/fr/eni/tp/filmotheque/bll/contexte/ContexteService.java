package fr.eni.tp.filmotheque.bll.contexte;

import fr.eni.tp.filmotheque.bo.Membre;

import java.util.List;

public interface ContexteService {
    public Membre charger(String email);
    public List<Membre> getListeMembres();
    }
