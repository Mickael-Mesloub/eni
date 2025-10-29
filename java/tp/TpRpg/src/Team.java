import java.util.ArrayList;

public class Team {
    public String name;
    public ArrayList<Battler> battlers;

    public Team(String name, ArrayList<Battler> battlers) {
        this.name = name;
        this.battlers = battlers;
    }

    public boolean isLooser(){
        boolean result = false;

        for(Battler battler : battlers) {
            result = battler.hp == 0;
        }

        return result;
    }
}