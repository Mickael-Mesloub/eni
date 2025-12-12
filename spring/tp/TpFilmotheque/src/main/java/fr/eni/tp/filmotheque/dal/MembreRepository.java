package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Membre;

public interface MembreRepository {
    Membre findMembreByUsername(String username);
}
