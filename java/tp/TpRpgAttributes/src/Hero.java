public class Hero extends Character {
    public String teamName;
    public String type;

    public Hero(String _name, String _teamName) {
        super(_name);
        teamName = _teamName;
        type = "Héros";
    }

    @Override
    public void showInfo() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Type : %s \n", type));
        message.append(String.format("Nom du personnage : %s \n", name));
        message.append(String.format("Équipe : %s \n", teamName));
        message.append("Attributs : \n");

        for (Attribute attribute : attributes) {
            message.append(String.format("- %s : %s \n", attribute.getName(), attribute.getValue()));
        }

        System.out.println(message);

    }
}
