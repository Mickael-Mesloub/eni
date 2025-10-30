public class Battler {
    public String name;
    public int hp;
    public int power;

    public Battler(String name, int hp, int power){
        this.name = name;
        this.hp = hp;
        this.power = power;
    }

    // TODO: refactor : param = Team.
    //  Boucler sur team et attack le joueur le plus faible (le moins d'HP)
    public void attack(Battler opponent){
        // Permet d'attaquer un adversaire : opponent.looseHP(power)
        String message = (String.format("⚔\uFE0F %s attacks %s!", name, opponent.name));
        System.out.println(message);
        opponent.loseHP(power);
    }

    // TODO: retirer de la liste le joueur qui a le moins d'HP
    public void loseHP(int amount) {
        // Fait descendre les points de vie d'un montant n (= amount)
        hp -= amount;
        String message = String.format("\uD83D\uDC94 %s lost %dHP.", name,amount);
        System.out.println(message);
    }
}
