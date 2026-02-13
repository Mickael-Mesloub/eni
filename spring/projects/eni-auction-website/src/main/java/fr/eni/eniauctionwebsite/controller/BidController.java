package fr.eni.eniauctionwebsite.controller;

import fr.eni.eniauctionwebsite.bll.*;
import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class BidController {
    private final UserService userService;
    private final ItemService itemService;
    private final BidService bidService;

    public BidController(BidService bidService, UserService userService, ItemService itemService) {
        this.bidService = bidService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @PostMapping("/auction/bid")
    public String placeBid(@RequestParam("itemId") int itemId,
                           @RequestParam("bidding-offer") int bidAmount,
                           Model model,
                           Authentication authentication, RedirectAttributes redirectAttributes) {

        User user = userService.getUserByLogin(authentication.getName());
        Item item = itemService.findItemById(itemId);

        // Vérification crédits insuffisants
        if (bidAmount > user.getCreditPoints()) {
            redirectAttributes.addFlashAttribute("error", "Vous n'avez pas assez de points pour enchérir avec ce montant.");
            return "redirect:/auction/details/" + itemId;
        }
        // Vérification enchère trop basse
        int currentPrice = (item.getCurrentPrice() > 0) ? item.getCurrentPrice() : item.getStartingPrice();
        if (bidAmount < currentPrice + 1) {
            redirectAttributes.addFlashAttribute("error", "Votre enchère doit être au moins à " + (currentPrice + 1) + " points.");
            return "redirect:/auction/details/" + itemId;
        }

        // Créer et mettre à jour l'enchère
        Bid bid = bidService.placeBid(bidAmount, user, itemId);
        if (bid != null) {
            bid = bidService.updateBid(bid);
            redirectAttributes.addFlashAttribute("validBid", true);
        } else {
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenue lors de la création de l'enchère.");
        }

        return "redirect:/auction/details/" + itemId;
    }
}
