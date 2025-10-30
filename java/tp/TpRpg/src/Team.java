import java.util.ArrayList;

public class Team {
    public String name;
    public ArrayList<Battler> battlers;

    public Team(String name, ArrayList<Battler> battlers) {
        this.name = name;
        this.battlers = battlers;
    }

    public boolean isLoser() {
        // Si tous les combattants sont morts, l'équipe a perdu
        for(Battler battler : battlers) {
            return battler.hp <= 0;
        }

        return false;
    }
}