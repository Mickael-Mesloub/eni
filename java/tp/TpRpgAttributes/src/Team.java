import java.util.ArrayList;

public class Team {
    private final String name;
    private final ArrayList<Character> characters;

    public Team(String _name, ArrayList<Character> _characters) {
        name = _name;
        characters = _characters;
    }

    /**
     *  Détermine si l'équipe est perdante
     * @return true si la liste characters est vide (= tous les characters morts)
     */
    public boolean isLoser() {
       return characters.isEmpty();
    }

    /**
     * Retirer un personnage mort de la liste de personnages
     * @param deadCharacter Le personnage mort
     */
    public void removeDeadCharacter(Character deadCharacter) {
        characters.remove(deadCharacter);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
}