public class Battler {
    private final String name;
    private int hp;
    private final int power;
    private final int initiative;

    public Battler(String _name, int _hp, int _power, int _initiative) {
        name = _name;
        hp = _hp;
        power = _power;
        initiative = _initiative;
    }

    /**
     * Attaquer l'équipe ennemie
     * @param defenderTeam L'équipe attaquée
     */
    public void attack(Team defenderTeam) {
        // TODO: target enemy with lowest hp amount
        // Taper le premier ennemi de la liste
        defenderTeam.getBattlers().stream().findFirst().ifPresent(defender -> strike(defender, defenderTeam));
    }

    /**
     * Faire baisser les points de vie ou tuer un ennemi
     * @param damage Nombre de dégâts infligés
     * @param defenderTeam Équipe dans laquelle se trouve le combattant attaqué
     */
    public void loseHP(int damage, Team defenderTeam) {

        // Fait descendre les points de vie d'un montant n (= damage) + affiche un message
        hp -= damage;
        System.out.printf("\n 🤕 %s lost %d HP. %d HP remaining", name, damage, hp);

        // Si le combattant meurt, le retirer de la liste des combattants de son équipe + affiche un message
        if(isDead()) {
            defenderTeam.removeDeadBattler(this);
            System.out.printf("\n ☠️ %s is dead!", name);
            System.out.printf("\n %s is out of the game!", name);
        }
    }

    /**
     * Détermine si un combattant est mort ou non
     * @return true s'il est mort (si ses hp sont inférieurs ou égaux à 0)
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Infliger des dégâts à l'ennemi
     * @param enemy Le combattant ennemi attaqué
     * @param defenderTeam L'équipe dans laquelle se trouve le combattant attaqué
     */
    public void strike(Battler enemy, Team defenderTeam) {
        // Permet d'attaquer un adversaire : enemy.loseHP(power)
        System.out.printf("\n ⚔️ %s attacks %s", name, enemy.name);
        enemy.loseHP(power, defenderTeam);
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public int getHp() {
        return hp;
    }

    public int getInitiative() {
        return initiative;
    }
}
