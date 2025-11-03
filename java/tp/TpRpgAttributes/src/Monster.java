public class Monster extends Character {
    private String species;
    private String type;

    public Monster(String _name, String _species, int _hp, int _abilityPower, int _initiative ) {
        super(_name, _hp, _abilityPower, _initiative);
        species = _species;
        type = "Monstre";
    }

    @Override
    public void showInfo() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Type : %s \n", type))
        .append(String.format("Nom du personnage : %s \n", getName()))
        .append(String.format("Espèce : %s \n", species))
        .append(String.format("Initiative : %d \n", getInitiative()))
        .append(String.format("HP : %d \n", getHp()))
        .append(String.format("Pouvoir : %d \n", getAbilityPower()))
        .append("Attributs : \n");

        for (Attribute attribute : getAttributes()) {
            message.append(String.format("- %s : %s \n", attribute.getName(), attribute.getValue()));
        }

        System.out.println(message);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
