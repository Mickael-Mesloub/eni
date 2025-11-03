public class Hero extends Character {
    private String teamName;
    private String type;

    public Hero(String _name, String _teamName, int _hp, int _abilityPower, int _initiative) {
        super(_name, _hp, _abilityPower, _initiative);
        teamName = _teamName;
        type = "Héros";
    }

    @Override
    public void showInfo() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Type : %s \n", type))
        .append(String.format("Nom du personnage : %s \n", getName()))
        .append(String.format("Équipe : %s \n", teamName))
        .append(String.format("Initiative : %d \n", getInitiative()))
        .append(String.format("HP : %d \n", getHp()))
        .append(String.format("Pouvoir : %d \n", getAbilityPower()))
        .append("Attributs : \n");

        for (Attribute attribute : getAttributes()) {
            message.append(String.format("- %s : %s \n", attribute.getName(), attribute.getValue()));
        }

        System.out.println(message);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
