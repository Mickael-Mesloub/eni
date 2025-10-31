public class GenericExampleApp {
    public static void main(String[] args) {

        Attribute<String> attrString = new Attribute<String>("Classe", "Mage");
        Attribute<Integer> attrInt = new Attribute<Integer>("Force", 14);

        System.out.printf("Attribut : %s - Valeur : %s \n", attrString.name, attrString.value);
        System.out.printf("Attribut : %s - Valeur : %d", attrInt.name, attrInt.value);

    }
}