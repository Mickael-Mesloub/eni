package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.auction.AuctionFilterDTO;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> findAllItems();
    Item saveItem(Item item);
    Optional<Item> getItemById(int id);
    void deleteItemById(int id);
    List<Item> getItemsByCategoryId(int categoryId);
    List<Item> getItemsByUserId(int userId);
    void deleteItemsByUserId(int userId);

    List<Item> findItemsByFilters(AuctionFilterDTO filters, User user);
}
