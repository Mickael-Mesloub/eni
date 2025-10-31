public class Battler {
    public String name;
    public int hp;
    public int power;
    public int initiative;

    public Battler(String _name, int _hp, int _power, int _initiative) {
        name = _name;
        hp = _hp;
        power = _power;
        initiative = _initiative;
    }

    // Méthode pour attaquer une équipe ennemie
    public void attack(Team defenderTeam) {
        // TODO: target enemy with lowest hp amount
        // Taper le premier ennemi de la liste
        Battler defender = defenderTeam.battlers.get(0);
            strike(defender, defenderTeam);
    }

    // Méthode pour faire baisser les points de vie ou tuer un ennemi
    public void loseHP(int damage, Team defenderTeam) {

        // Fait descendre les points de vie d'un montant n (= damage) + affiche un message
        hp -= damage;
        System.out.println(String.format("🤕 %s lost %d HP. %d HP remaining", name, damage, hp));

        // Si le combattant meurt, le retirer de la liste des combattants de son équipe + affiche un message
        if(isDead()) {
            defenderTeam.removeDeadBattler(this);
            System.out.println(String.format("☠️ %s is dead!", name));
            System.out.println(String.format("%s is out of the game!", name));
        }
    }

    // Méthode qui renvoie un boolean : true si les hp du battler sont à 0, sinon false
    public boolean isDead() {
        return hp <= 0;
    }

    // TODO: need more tests to determine if this is useful or not
    public boolean canAttack(Battler opponent) {
        return !isDead() && !opponent.isDead();
    }

    // Méthode pour infliger des dégâts à l'ennemi
    public void strike(Battler opponent, Team defenderTeam) {
        // Permet d'attaquer un adversaire : opponent.loseHP(power)
        System.out.println(String.format("⚔️ %s attacks %s", name, opponent.name));
        opponent.loseHP(power, defenderTeam);
    }
}
