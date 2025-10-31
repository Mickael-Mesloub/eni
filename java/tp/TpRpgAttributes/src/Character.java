import java.util.ArrayList;

public class Character {
    String name;
    ArrayList<Attribute> attributes;

    public Character(String _name) {
        name = _name;
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
}
