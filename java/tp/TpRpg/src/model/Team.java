package model;

import java.util.ArrayList;

public class Team {
    private final String teamName;
    private final ArrayList<Character> characters;

    public Team(String _teamName, ArrayList<Character> _characters) {
        teamName = _teamName;
        characters = _characters;
    }

    /**
     * Détermine si l'équipe est perdante
     * @return true si tous ses personnages sont morts
     */
    public boolean isLoser() {
       return characters.isEmpty();
    }

    /**
     * Retire un personnage mort de la liste des personnages
     * @param deadCharacter Le personnage mort (= qui a perdu tous ses hp)
     */
    public void removeDeadCharacter(Character deadCharacter) {
        characters.remove(deadCharacter);
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public String getName() {
        return teamName;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
}