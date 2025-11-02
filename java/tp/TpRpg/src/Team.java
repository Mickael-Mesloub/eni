import java.util.ArrayList;

public class Team {
    public String name;
    public ArrayList<Battler> battlers;

    public Team(String _name, ArrayList<Battler> _battlers) {
        name = _name;
        battlers = _battlers;
    }

    // Méthode pour déterminer si l'équipe est perdante : true si la liste battlers est vide (= tous les battlers morts)
    public boolean isLoser() {
       return battlers.isEmpty();
    }

    // Méthode pour retirer un combattant mort de la liste
    public void removeDeadBattler(Battler deadBattler) {
        battlers.remove(deadBattler);
    }
}