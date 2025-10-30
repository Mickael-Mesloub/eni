import java.util.ArrayList;
import java.util.List;

public class TpInventoryApp {
    public static void main(String[] args) {
        // Instancier l'inventaire
        Inventory inventory = new Inventory();

        // Instancier les items
        Item sword = new Item("Épée", 1);
        Item shield = new Item("Bouclier", 1);
        Item healthPotion = new Item("Potion de vie", 5);
        Item brokenBow = new Item("Arc brisé", 1);
        Item brokenArrow = new Item("Flêche cassée", 8);

        // Liste d'items à ajouter
        ArrayList<Item> itemsToAdd = new ArrayList<Item>(List.of(sword, shield, healthPotion, brokenBow,
                brokenArrow));

        // Liste d'items à supprimer
        ArrayList<Item> itemsToRemove = new ArrayList<Item>(List.of(brokenBow, brokenArrow));

        for (Item item : itemsToAdd) {
            inventory.addItem(item);
        }

        inventory.displayItems();

        //TODO: test removeItem()
    }
}