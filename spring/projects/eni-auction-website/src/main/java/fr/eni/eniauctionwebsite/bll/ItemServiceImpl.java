package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.ItemCategory;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.auction.AuctionFilterDTO;
import fr.eni.eniauctionwebsite.controller.dto.item.ItemDTO;
import fr.eni.eniauctionwebsite.dal.ItemRepository;
import fr.eni.eniauctionwebsite.exception.ItemCategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private ItemCategoryService itemCategoryService;
    private BidService bidService;

    public ItemServiceImpl(ItemRepository itemRepository, ItemCategoryService itemCategoryService, BidService bidService) {
        this.itemRepository = itemRepository;
        this.itemCategoryService = itemCategoryService;
        this.bidService = bidService;
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAllItems();
    }

    @Override
    public Item findItemById(int id) {
        return itemRepository.getItemById(id)
                .orElseThrow(() -> new ItemCategoryNotFoundException(id));
    }

    @Override
    public List<Item> findItemsByCategoryId(int categoryId) {
        return itemRepository.getItemsByCategoryId(categoryId);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.saveItem(item);
    }

    @Override
    public void deleteItemById(int id) {
        Item item = itemRepository.getItemById(id).get();
        List<Bid> bids = bidService.findAllBidsByItemId(id);

        if(item.isRetrieved()){
            bids.forEach(bid -> bidService.deleteBidById(bid.getId()));
        }
        if(bids.isEmpty()){
            itemRepository.deleteItemById(id);
        }
    }

    @Override
    public void deleteItemsByUserId(int userId) {
        itemRepository.deleteItemsByUserId(userId);
    }
    @Override
    public Item createItemFromDTO(ItemDTO itemDTO, User user) {

        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setStartingPrice(itemDTO.getStartingPrice());
        item.setAuctionStartDate(itemDTO.getAuctionStartDate());
        item.setAuctionEndDate(itemDTO.getAuctionEndDate());
        item.setCurrentPrice(itemDTO.getStartingPrice());
        item.setAuctionCreator(user);
        item.setRetrievalAddress(user.getAddress());
        item.setSold(false);
        item.setRetrieved(false);

        ItemCategory category = itemCategoryService
                .findItemCategoryById(itemDTO.getCategoryId());
        item.setCategory(category);

        if (itemDTO.getImagePath() == null || itemDTO.getImagePath().isBlank()) {
            item.setImagePath("/images/default_image.png");
        } else {
            item.setImagePath(itemDTO.getImagePath());
        }

        return item;
    }



    @Override
    public List<Item> findItemsByFilters(AuctionFilterDTO filters, User user) {
        return itemRepository.findItemsByFilters(filters, user);
    }

    @Override
    public List<Item> findItemsByUserId(int userId){
        return itemRepository.getItemsByUserId(userId);
    }

    @Override
    public List<Item> getActiveItemsFromUser(List<Item> items, User user){
        List<Item> activeItems = new ArrayList<>();
        items.forEach(item -> {
            if (!item.isRetrieved()) {
                if(!bidService.findAllBidsByItemId(item.getId()).isEmpty()) {
                    activeItems.add(item);
                }
            }
        });
        return activeItems;
    }

}
