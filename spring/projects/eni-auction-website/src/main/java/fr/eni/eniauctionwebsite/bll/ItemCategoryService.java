package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.ItemCategory;

import java.util.List;
import java.util.Optional;

public interface ItemCategoryService {
    List<ItemCategory> findAllItemCategories();
    ItemCategory findItemCategoryById(int id);
}
