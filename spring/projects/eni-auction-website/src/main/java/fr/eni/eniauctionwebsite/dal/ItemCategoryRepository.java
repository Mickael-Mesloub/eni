package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.ItemCategory;

import java.util.List;
import java.util.Optional;

public interface ItemCategoryRepository {

    List<ItemCategory> findAllItemCategories();

    Optional<ItemCategory> findItemCategoryById(int id);

}
