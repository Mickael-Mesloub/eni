import java.util.ArrayList;
import java.util.List;

public class TpInventoryApp {
    public static void main(String[] args) {
        // Instancier l'inventaire
        Inventory inventory = new Inventory();

        // Instancier les items
        Item sword = new Item("Épée", 1);
        Item sword2 = new Item("Épée", 4);
        Item shield = new Item("Bouclier", 1);
        Item healthPotion = new Item("Potion de vie", 5);
        Item brokenBow = new Item("Arc brisé", 1);
        Item brokenArrow = new Item("Flêche cassée", 8);
        Item sword3 = new Item("Épée", 1);

        // Liste d'items à ajouter
        ArrayList<Item> itemsToAdd = new ArrayList<Item>(List.of(sword, sword2, shield, healthPotion, brokenBow,
                brokenArrow));

        // Liste d'items à supprimer
        ArrayList<Item> itemsToRemove = new ArrayList<Item>(List.of(brokenBow, brokenArrow, sword3));

        for (Item item : itemsToAdd) {
            inventory.addItem(item);
        }

        // Afficher le contenu de l'inventaire
        inventory.displayItems();

        // Supprimer des items
        for (Item item : itemsToRemove) {
            inventory.removeItem(item);
        }

        // Réafficher l'inventaire mis à jour
        inventory.displayItems();

    }

}