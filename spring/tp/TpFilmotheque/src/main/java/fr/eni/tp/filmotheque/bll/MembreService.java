package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;

public interface MembreService {
    Membre consulterMembreParPseudo(String pseudo);
}
