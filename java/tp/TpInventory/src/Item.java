public class Item {
    // Nom de l'item
    public String name;
    // Quantité de l'item
    public int quantity;

    // Constructeur avec valeurs par défaut
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Méthode pour afficher les détails de l'item
    @Override
    public String toString() {
        return String.format("%s x%d", name, quantity);
    }
}
