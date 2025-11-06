package model;

import java.util.ArrayList;

public class Team {
    private final String teamName;
    private final ArrayList<Battler> battlers;

    public Team(String _teamName, ArrayList<Battler> _battlers) {
        teamName = _teamName;
        battlers = _battlers;
    }

    /**
     * Détermine si l'équipe est perdante
     * @return true si tous ses combattants sont morts
     */
    public boolean isLoser() {
       return battlers.isEmpty();
    }

    /**
     * Retire un combattant mort de la liste des combattants
     * @param deadBattler Le combattant mort (= qui a perdu tous ses hp)
     */
    public void removeDeadBattler(Battler deadBattler) {
        battlers.remove(deadBattler);
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public String getName() {
        return teamName;
    }

    public ArrayList<Battler> getBattlers() {
        return battlers;
    }
}