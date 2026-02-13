package fr.eni.eniauctionwebsite.controller;

import fr.eni.eniauctionwebsite.bll.*;
import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.item.ItemDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/auction")
public class AuctionController {
    private final ItemCategoryService itemCategoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final BidService bidService;
    private final AuctionService auctionService;

    public AuctionController(ItemCategoryService itemCategoryService, ItemService itemService, UserService userService, BidService bidService, AuctionService auctionService) {
        this.itemCategoryService = itemCategoryService;
        this.itemService = itemService;
        this.userService = userService;
        this.bidService = bidService;
        this.auctionService = auctionService;
    }

    //Récupère l'id dans l'URL lorsque on clique sur une des cartes d'items sur la page d'accueil pour afficher les détails de la vente
    @GetMapping("/details/{id}")
    public String getDetailsAuction(
            @PathVariable int id,
            Model model,
            Authentication authentication
    ) {
        // TODO: procédure stockée?
        // Récupération de l'item
        Item item = itemService.findItemById(id);

        // Récupération de la meilleure enchère
        Bid highestBid = bidService.findHighestBidByItemId(id);

        //Récupération de la liste de bids
        List<Bid> bids = bidService.findAllBidsByItemId(id);
        int bidCount = bids.size();

        // Verifier si l'enchère est terminée
        item.setSold(auctionService.isAuctionEnded(item));

        // Calcul du prix actuel
        int currentPrice = auctionService.updateCurrentPrice(highestBid, item);

        // Trouver le meilleur enchérisseur
        User highestBidder = auctionService.getHighestBidder(highestBid, item);
        item.setCurrentPrice(currentPrice);

        // Mise à jour de l'item avec le prix actuel
        itemService.saveItem(item);

        model.addAttribute("item", item);
        model.addAttribute("bid", highestBid);           // peut être null
        model.addAttribute("highestBidder", highestBidder);
        model.addAttribute("currentPrice", currentPrice);
        model.addAttribute("bidCount", bidCount);

        // Récupération de l'utilisateur connecté
        if (authentication != null) {
            User user = userService.getUserByLogin(authentication.getName());
            model.addAttribute("user", user);
        }

        return "view-auction-details";
    }


    //Affiche la page de création d'une vente
    @GetMapping("/create")
    public String getCreateAuction(Model model, Authentication authentication) {

        User user = userService.getUserByLogin(authentication.getName());

        ItemDTO itemDto = new ItemDTO();
        itemDto.setAuctionStartDate(LocalDateTime.now().plusMinutes(5));
        itemDto.setAuctionEndDate(LocalDateTime.now().plusMinutes(5).plusWeeks(1));

        model.addAttribute("user", user);
        model.addAttribute("itemDto", itemDto);
        model.addAttribute("categories", itemCategoryService.findAllItemCategories());

        return "view-create-auction";
    }


    // Créé la nouvelle vente en base à partir des données du formulaire
    @PostMapping("/create")
    public String createAuction(@Valid @ModelAttribute("itemDto") ItemDTO itemDto,
                                BindingResult bindingResult,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes,
                                Model model) throws IOException {

        User user = userService.getUserByLogin(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("categories", itemCategoryService.findAllItemCategories());

        if (bindingResult.hasErrors()) {
            return "view-create-auction";
        }

        // Création du dossier uploads si inexistant
        // ce lien sera bien sûr ailleurs au déploiement (sur le serveur quelque part)

        String uploadDir = System.getProperty("user.dir") + "/uploads/images";

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

        // Sauvegarde du fichier si présent
        MultipartFile imageFile = itemDto.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {

            long maxSize = 3 * 1024 * 1024; // 3 Mo

            if (imageFile.getSize() > maxSize) {
                bindingResult.rejectValue("imageFile", "error.imageFile",
                        "L'image ne doit pas dépasser 3 Mo");
                return "view-create-auction";
            }

            String fileName = Paths.get(Objects.requireNonNull(imageFile.getOriginalFilename())).getFileName().toString();
            File destination = new File(uploadDirFile, fileName);
            imageFile.transferTo(destination);
            itemDto.setImagePath("/uploads/images/" + fileName);
        } else {
            itemDto.setImagePath("/images/default_image.png");
        }

        // Création et sauvegarde de l’item
        Item item = itemService.createItemFromDTO(itemDto, user);
        itemService.saveItem(item);

        redirectAttributes.addFlashAttribute("successMessage", "Nouvelle vente créée avec succès !");

        return "redirect:/auction/details/" + item.getId();
    }



    @PostMapping("/delete/{id}")
    public String deleteAuction(@PathVariable int id, Authentication authentication, RedirectAttributes redirectAttributes) {

        User user = userService.getUserByLogin(authentication.getName());
        Item item = itemService.findItemById(id);

        if (item != null && user != null) {
            if (item.getAuctionCreator().getId() == user.getId()) {
                if (item.getBids().isEmpty() || item.isRetrieved()) {
                    itemService.deleteItemById(id);
                }
            }
        }
        redirectAttributes.addFlashAttribute("auction_deleted", true);
        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    public String deleteAuctionGet(@PathVariable int id, Authentication authentication, RedirectAttributes redirectAttributes) {
        deleteAuction(id, authentication, redirectAttributes);
        redirectAttributes.addFlashAttribute("auction_deleted", true);
        return "redirect:/home";
    }

    @GetMapping("/end/{itemId}")
    public String concludeAuction(
            @PathVariable int itemId, RedirectAttributes redirectAttributes) {
        auctionService.concludeAuction(itemId);
        redirectAttributes.addFlashAttribute("successMessage", "Cette vente est maintenant terminée et archivée.");

        return "redirect:/home";
    }

}
