public class Monster extends Character {
    public String species;
    public String type;

    public Monster(String _name, String _species) {
        super(_name);
        species = _species;
        type = "Monstre";
    }

    @Override
    public void showInfo() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Type : %s \n", type));
        message.append(String.format("Nom du personnage : %s \n", name));
        message.append(String.format("Espèce : %s \n", species));
        message.append("Attributs : \n");

        for (Attribute attribute : attributes) {
            message.append(String.format("- %s : %s \n", attribute.getName(), attribute.getValue()));
        }

        System.out.println(message);

    }
}
