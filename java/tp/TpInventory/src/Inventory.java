import java.util.ArrayList;

public class Inventory {
    // Liste d'items de l'inventaire
    ArrayList<Item> items;

    // Constructeur qui initialise la liste items
    public Inventory () {
        items = new ArrayList<>();
    }

    // Méthode pour ajouter un item à la liste
    public void addItem(Item newItem){
        items.add(newItem);
        System.out.println(String.format("%s x%d ajouté à l'inventaire.", newItem.name, newItem.quantity));
    };

    // Méthode pour afficher les items de la liste
    public void displayItems(){
        StringBuilder message = new StringBuilder(String.format("Contenu de l\'inventaire :\n"));
        for(Item item : items) {
            message.append(String.format("%s x%d\n", item.name, item.quantity));
        }

        System.out.println(message);
    }

    // TODO: test method
    // Méthode pour supprimer un item de la liste
    public void removeItem(String nameOfItemToRemove){
        for(Item item : items) {
            if(item.name.equals(nameOfItemToRemove)) {
                items.remove(item);
            }
        }
    }
}

