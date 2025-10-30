public class Battler {
    public String name;
    public int hp;
    public int power;

    public Battler(String name, int hp, int power){
        this.name = name;
        this.hp = hp;
        this.power = power;
    }

    public void attack(Battler opponent){
        // Permet d'attaquer un adversaire : opponent.loseHP(power)
        System.out.println(String.format("⚔️ %s attacks %s", name, opponent.name));
        opponent.loseHP(power);
    }

    public void loseHP(int damage) {
        // Fait descendre les points de vie d'un montant n (= damage)
        hp -= damage;
        System.out.println(String.format("🤕 %s lost %d HP", name, damage));

        // Si le combattant n'a plus d'hp, il est mort
        if(hp <= 0) {
            System.out.println(String.format("☠️ %s is dead!", name));
        }
    }
}
