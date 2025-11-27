package fr.eni.tp.chifoumi.bll;

import fr.eni.tp.chifoumi.bo.Partie;

public interface ChifoumiService {
    public void gameRules(String playerChoice, String serverChoice);
    public Partie play(String playerChoice);
}
