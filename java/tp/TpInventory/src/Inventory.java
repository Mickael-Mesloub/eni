import java.util.ArrayList;

public class Inventory {
    // Liste d'items de l'inventaire
    ArrayList<Item> items;

    // Constructeur qui initialise la liste items
    public Inventory () {
        items = new ArrayList<>();
    }

    // Méthode pour ajouter un item à la liste
    public void addItem(Item item){
        // Si item existe, on le cherche dans la liste
        // on cherche le premier item qui a le même nom
        // Si on trouve pas, null (orElse(null)
       Item foundItem = items.stream().filter((i ) -> i.name.equals(item.name)).findFirst().orElse(null);

       // Si j'ai trouvé cet item, j'augmente sa quantité
       if(foundItem != null) {
           foundItem.quantity += item.quantity;
           // Message d'ajout de quantité d'un objet existant
           System.out.println(String.format("➕ %s x%d ajouté à l'inventaire.", item.name, item.quantity));
           return;
       }

       // S'il n'existe pas, je l'ajoute à l'inventaire
       items.add(item);

       // Message d'ajout de nouvel item
        System.out.println(String.format("\uD83C\uDF1F Nouvel item %s x%d ajouté à l'inventaire.", item.name, item.quantity));
    };

    // Méthode pour afficher les items de la liste
    public void displayItems(){
        StringBuilder message = new StringBuilder(String.format("\nContenu de l\'inventaire :\n"));
        for(Item item : items) {
            message.append(String.format("• %s x%d\n", item.name, item.quantity));
        }

        System.out.println(message);
    }

    // Méthode pour supprimer un item de la liste
    public void removeItem(Item item){
        // Boucler sur la liste d'items pour chercher l'item
        Item foundItem = items.stream().filter((i ) -> i.name.equals(item.name)).findFirst().orElse(null);

        // Si l'item existe dans l'inventaire
        if(foundItem != null) {
            // Si quantité indiquée >= quantité dans l'inventaire, supprimer totalement l'item
            if(item.quantity >= foundItem.quantity) {
                items.remove(foundItem);
                // Message de suppression totale d'un objet
                System.out.println(String.format("❌ %s supprimé de l'inventaire.", foundItem.name));

            } else {
                // Sinon, juste diminuer la quantité dans l'inventaire
                foundItem.quantity -= item.quantity;
                // Message de suppression d'une quantité d'un objet existant
                System.out.println(String.format("➖ %s x%d supprimé de l'inventaire.", item.name, item.quantity));
            }
        }
    }
}

