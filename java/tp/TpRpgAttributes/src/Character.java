import java.util.ArrayList;

public class Character {
    private final String name;
    private int hp;
    private int abilityPower;
    private int initiative;
    private ArrayList<Attribute> attributes;

    public Character(String _name, int _hp, int _abilityPower, int _initiative) {
        name = _name;
        hp = _hp;
        abilityPower = _abilityPower;
        initiative = _initiative;
        attributes = new ArrayList<Attribute>();
    }

    // Ajouter un attribut à la liste
    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    // J'ai créé une autre addAttribute car plus simple à utiliser dans Main (pas besoin de faire new Attribute... à chaque fois).
    public void addAttribute(String _name, String _value) {
        attributes.add(new Attribute(_name, _value));
    }

    // Afficher le nom du personnage et la liste de ses attributs
    public void showInfo() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Nom du personnage : %s \n", name));

        message.append("Attributs : \n");

        for (Attribute attribute : attributes) {
            message.append(String.format("- %s : %s \n", attribute.getName(), attribute.getValue()));
        }

        System.out.println(message);
    }

    /**
     * Utilise la compétence du personnage pour attaquer un ennemi
     * @param enemy L'ennemi attaqué
     * @param defenderTeam L'équipe à laquelle l'ennemi appartient
     * @param abilityPower La puissance de la compétence utilisée par l'attaquant
     */
    private void useAbility(Character enemy, Team defenderTeam, int abilityPower) {
        // Permet d'attaquer un ennemi : enemy.loseHP(power)
        System.out.printf("\n ⚔️ %s attacks %s", name, enemy.name);
        enemy.loseHP(abilityPower, defenderTeam);
    }

    /**
     * Fait descendre les points de vie d'un personnage
     * @param damage Le nombre de points de vie à enlever
     * @param defenderTeam L'équipe à laquelle appartient le personnage qui subit des dégâts
     */
    private void loseHP(int damage, Team defenderTeam) {

        // Fait descendre les points de vie d'un montant n (= damage) + affiche un message
        hp -= damage;
        System.out.printf("\n 🤕 %s lost %d HP. %d HP remaining", name, damage, hp);

        // Si le combattant meurt, le retirer de la liste des combattants de son équipe + affiche un message
        if(isDead()) {
            defenderTeam.removeDeadCharacter(this);
            System.out.printf("\n ☠️ %s is dead!", name);
            System.out.printf("\n %s is out of the game!", name);
        }
    }

    /**
     * Utilise la compétence du personnage pour attaquer un ennemi
     * @param defenderTeam L'équipe à laquelle appartient le personnage attaqué
     */
    public void attack(Team defenderTeam) {
        // Taper le premier ennemi de la liste
        defenderTeam.getCharacters().stream().findFirst().ifPresent(defender -> useAbility(defender, defenderTeam, abilityPower));
    }

    /**
     *  Vérifie si un personnage est mort
     * @return true si le montant d'hp du personnage est inférieur ou égal à 0
     */
    public boolean isDead() {
        return hp <= 0;
    }

    // ------------------- GETTERS AND SETTERS------------------- \\

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }


    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
}
