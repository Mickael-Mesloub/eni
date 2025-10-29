import java.util.ArrayList;
import java.util.List;

public class TpRpgApp {
    public static void main(String[] args) {
        Battler me = new Battler("sUp3rK3v", 50, 20);
        Battler opponent = new Battler("n0obl@rd", 60, 6);

        Team team1 = new Team("Player Team", new ArrayList<Battler>(List.of(me)));
        Team team2 = new Team("Enemy Team", new ArrayList<Battler>(List.of(opponent)));

        // TODO: déplacer dans Game play() + ajouter team gagnante
        while (opponent.hp > 0) {
            me.attack(opponent);
        }
    }
}