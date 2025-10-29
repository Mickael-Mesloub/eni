public class TpRpgApp {
    public static void main(String[] args) {
        System.out.println("Hello TpRpg!");

        // TODO: ajouter les teams

        Battler me = new Battler("sUp3rK3v", 50, 20);
        Battler opponent = new Battler("n0obl@rd", 60, 6);

        // TODO: déplacer dans Game play() + ajouter team gagnante
        while (opponent.hp > 0) {
            me.attack(opponent);
        }
    }
}