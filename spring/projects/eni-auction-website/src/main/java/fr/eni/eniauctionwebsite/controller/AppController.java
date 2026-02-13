package fr.eni.eniauctionwebsite.controller;

import fr.eni.eniauctionwebsite.bll.ItemCategoryService;
import fr.eni.eniauctionwebsite.bll.ItemService;
import fr.eni.eniauctionwebsite.bll.UserService;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.ItemCategory;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.auction.AuctionFilterDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class AppController {

    private final ItemCategoryService itemCategoryService;
    private final ItemService itemService;
    private final UserService userService;

    public AppController(ItemCategoryService itemCategoryService, ItemService itemService, UserService userService) {
        this.itemCategoryService = itemCategoryService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping({"/home", "/"})
    public String getHome(
            AuctionFilterDTO filters,
            Authentication authentication,
            Model model
    ) {

        //Test authentification
        User user;
        if (authentication != null) {
            user = userService.getUserByLogin(authentication.getName());
        } else {
            user = null;
        }

        List<Item> items = itemService.findItemsByFilters(filters, user);

        model.addAttribute("filters", filters);
        model.addAttribute("items", items);
        model.addAttribute("categories", itemCategoryService.findAllItemCategories());

        return "view-home";
    }

}
