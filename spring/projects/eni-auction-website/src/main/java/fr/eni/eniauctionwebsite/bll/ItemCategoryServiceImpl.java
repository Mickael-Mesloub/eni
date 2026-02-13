package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.ItemCategory;
import fr.eni.eniauctionwebsite.dal.ItemCategoryRepository;
import fr.eni.eniauctionwebsite.exception.ItemCategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("itemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Override
    public List<ItemCategory> findAllItemCategories() {
        return itemCategoryRepository.findAllItemCategories();
    }

    @Override
    public ItemCategory findItemCategoryById(int id) {
        return itemCategoryRepository.findItemCategoryById(id)
                .orElseThrow(() -> new ItemCategoryNotFoundException(id));
    }

}
