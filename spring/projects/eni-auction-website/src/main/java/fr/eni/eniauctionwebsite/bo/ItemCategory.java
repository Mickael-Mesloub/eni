package fr.eni.eniauctionwebsite.bo;

import java.util.List;
import java.util.Objects;

public class ItemCategory {
    private int id;
    private String description;

    private List<Item> items;

    // CONSTRUCTORS
    public ItemCategory() {
    }
    public ItemCategory(int id, String description, List<Item> items) {
        this.id = id;
        this.description = description;
        this.items = items;
    }
    public ItemCategory(List<Item> items, String description) {
        this.items = items;
        this.description = description;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    // EQUALS HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemCategory that = (ItemCategory) o;
        return id == that.id && Objects.equals(description, that.description) && Objects.equals(items, that.items);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, description, items);
    }

    // GETTERS / SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
