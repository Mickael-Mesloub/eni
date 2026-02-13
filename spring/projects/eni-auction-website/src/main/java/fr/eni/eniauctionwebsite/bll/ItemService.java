package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.auction.AuctionFilterDTO;
import fr.eni.eniauctionwebsite.controller.dto.item.ItemDTO;

import java.util.List;

public interface ItemService {

    List<Item> findAllItems();
    Item findItemById(int id);
    List<Item> findItemsByCategoryId(int categoryId);
    List<Item> findItemsByUserId(int userId);
    Item saveItem(Item item);
    void deleteItemById(int id);
    void deleteItemsByUserId(int userId);
    Item createItemFromDTO(ItemDTO itemDTO, User user);
    List<Item> getActiveItemsFromUser(List<Item> items, User user);
    List<Item> findItemsByFilters(AuctionFilterDTO filterDTO, User user);

}
