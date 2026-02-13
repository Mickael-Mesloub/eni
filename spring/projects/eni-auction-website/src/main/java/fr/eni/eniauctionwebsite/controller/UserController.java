package fr.eni.eniauctionwebsite.controller;

import fr.eni.eniauctionwebsite.bll.BidService;
import fr.eni.eniauctionwebsite.bll.ItemService;
import fr.eni.eniauctionwebsite.bll.UserService;
import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.Bid;
import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.user.UpdatePasswordDTO;
import fr.eni.eniauctionwebsite.controller.dto.user.UpdateUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final BidService bidService;
    private UserService userService;
    private ItemService itemService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, ItemService itemService, PasswordEncoder passwordEncoder, BidService bidService) {
        this.userService = userService;
        this.itemService = itemService;
        this.passwordEncoder = passwordEncoder;
        this.bidService = bidService;
    }

    @GetMapping("/my-profile")
    public String myProfile(
            @RequestParam(value = "updated", required = false) String updated,
            Authentication authentication,
            Model model
    ) {
        User user = userService.getUserByLogin(authentication.getName());
        User connectedUser = userService.getUserByLogin(authentication.getName());

        model.addAttribute("user", user);
        model.addAttribute("connectedUser", connectedUser);

        if("profile".equals(updated)) {
            String message = "Votre profil a été mis à jour avec succès !";
            model.addAttribute("successMessage", message);
        }

        return "view-profile";
    }

    @GetMapping("/my-profile/update")
    public String getUpdateProfile(
            Authentication authentication,
            Model model
    ) {
        User user = userService.getUserByLogin(authentication.getName());
        Address address = user.getAddress();
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setStreet(address.getStreet());
        updateUserDTO.setZipcode(address.getZipcode());
        updateUserDTO.setCity(address.getCity());

        BeanUtils.copyProperties(user, updateUserDTO);

        model.addAttribute("updateUserDTO", updateUserDTO);

        return "view-update-profile";
    }

    @PostMapping("/my-profile/update")
    public String postUpdateMyProfile(
            Authentication authentication,
            @Valid @ModelAttribute("updateUserDTO") UpdateUserDTO updateUserDTO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        List<String> allUsernames = new ArrayList<>();
        List<String> allEmails = new ArrayList<>();
        List<User> allUsers = userService.getAllUsers();

        allUsers.forEach((user) -> {
            allUsernames.add(user.getUsername());
            allEmails.add(user.getEmail());
        });

        // Get authentified user
        User connectedUser = userService.getUserByLogin(authentication.getName());

        // Check if a user already exists in db with username provided in username field
        boolean doesUsernameAlreadyExist = allUsernames.stream().anyMatch(name -> name.equals(updateUserDTO.getUsername())); // TODO: try catch? if User not found

        // Check if a user already exists in db with email provided in email field
        boolean doesEmailAlreadyExist = allEmails.stream().anyMatch(email -> email.equals(updateUserDTO.getEmail())); // TODO: try catch? if User not found

        // Check if user modified his username
        boolean isUsernameModified = !updateUserDTO.getUsername().equals(connectedUser.getUsername());

        // Check if user modified his email
        boolean isEmailModified = !updateUserDTO.getEmail().equals(connectedUser.getEmail());

        // If user modified his username AND this new username already exists in db, show error
        if (isUsernameModified && doesUsernameAlreadyExist) {
            result.rejectValue(
                    "username",
                    "user.username.exists",
                    "Ce pseudo est déjà pris"
            );
        }

        // If user modified his email AND this new email already exists in db, show error
        if (isEmailModified && doesEmailAlreadyExist) {
            result.rejectValue(
                    "email",
                    "user.email.exists",
                    "Cet email est déjà pris"
            );
        }

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                logger.error(objectError.getDefaultMessage());
            });

            return "view-update-profile";
        }

        Address updatedAddress = new Address(
                connectedUser.getAddress().getId(),
                updateUserDTO.getStreet(),
                updateUserDTO.getZipcode(),
                updateUserDTO.getCity()
        );

        connectedUser.setAddress(updatedAddress);

        BeanUtils.copyProperties(updateUserDTO, connectedUser);

        userService.updateUser(connectedUser);

        // If username and/or email are modified, logout + redirect user + display message
        if(isUsernameModified && isEmailModified) {
            new SecurityContextLogoutHandler()
                    .logout(request, response,
                            SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login?reason=usernameAndEmailChanged";
        } else if(isEmailModified) {
            new SecurityContextLogoutHandler()
                    .logout(request, response,
                            SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login?reason=emailChanged";
        } else if(isUsernameModified) {
            new SecurityContextLogoutHandler()
                    .logout(request, response,
                            SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login?reason=usernameChanged";
        }

        return "redirect:/profile/my-profile?updated=profile";
    }


    @GetMapping("/my-profile/update-password")
    public String getUpdatePassword(Model model) {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO("", "", "");

        model.addAttribute("updatePasswordDTO", updatePasswordDTO);

        return "view-update-password";
    }
    
    @PostMapping("/my-profile/update-password")
    public String postUpdatePassword(
            Authentication authentication,
            @Valid @ModelAttribute("updatePasswordDTO") UpdatePasswordDTO updatePasswordDTO,
            BindingResult result,
             HttpServletRequest request,
            HttpServletResponse response
    ) {
        User connectedUser = userService.getUserByLogin(authentication.getName());
        boolean currentPasswordNotEmpty = updatePasswordDTO.getCurrentPassword() != null && !updatePasswordDTO.getCurrentPassword().isEmpty();
        boolean newPasswordNotEmpty = updatePasswordDTO.getNewPassword() != null && !updatePasswordDTO.getNewPassword().isEmpty();
        boolean confirmNewPasswordNotEmpty = updatePasswordDTO.getConfirmNewPassword() != null && !updatePasswordDTO.getConfirmNewPassword().isEmpty();

        boolean currentPasswordsMatch = passwordEncoder.matches(updatePasswordDTO.getCurrentPassword(), connectedUser.getPassword());
        boolean newPasswordsMatch = updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmNewPassword());

        // Check if current password matches with password in base
        if(currentPasswordNotEmpty && !currentPasswordsMatch) {
            // if currentPassword and password in base don't match, put currentPassword in error
            result.rejectValue(
                    "currentPassword",
                    "password.mismatch",
                    "Votre mot de passe actuel est incorrect"
            );
        }

        // Check if passwords fields are filled and if newPassword and confirmNewPassword match
        if (newPasswordNotEmpty
                && confirmNewPasswordNotEmpty
                && !newPasswordsMatch) {

            // if passwords don't match, put confirmPassword in error
            result.rejectValue(
                    "confirmNewPassword",
                    "password.mismatch",
                    "Les mots de passe ne correspondent pas"
            );
        }

        logger.info(updatePasswordDTO.toString());

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                logger.error(objectError.getDefaultMessage());
            });

            return "view-update-password";
        }

        connectedUser.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));

        userService.updateUser(connectedUser);

        new SecurityContextLogoutHandler()
                .logout(request, response,
                        SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login?reason=passwordChanged";

    }

    @GetMapping("/{username}")
    public String checkProfile(
            @PathVariable(name = "username") String username, Authentication authentication,
            Model model
    ) {
        User user = userService.getUserByUsername(username);
        User connectedUser = userService.getUserByLogin(authentication.getName());

        model.addAttribute("user", user);
        model.addAttribute("connectedUser", connectedUser);

        return "view-profile";
    }

    @GetMapping("/delete")
    public String delete(
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {

        User user = userService.getUserByLogin(authentication.getName());
        List<Item> items = itemService.findItemsByUserId(user.getId());
        List<Bid> bids = bidService.findAllBidsByUserId(user.getId());
        
        // check if there are active sales
        if (!items.isEmpty()) {
            List<Item> activeItems = itemService.getActiveItemsFromUser(items, user);

            // if there are active sales, cancel the delete profile
            if (!activeItems.isEmpty()) {
                redirectAttributes.addFlashAttribute("user_deleted_items", false);
                return "redirect:/profile/my-profile/update";
            }
        }
        if (!bids.isEmpty()) {
            List<Bid> activeBids = bidService.getActiveBidsFromUser(bids, user);

            // if there are active bids (= not the highest bid) from the user, cancel the delete profile
            if (!activeBids.isEmpty()) {
                redirectAttributes.addFlashAttribute("user_deleted_bids", false);
                return "redirect:/profile/my-profile/update";
            }
        }
        // if there are no active sales && bids, delete the profile
        // TODO: to keep a trace of the bids, maybe have a default "DELETED USER" profile?
        bidService.deleteBidsByUserId(user.getId());
        userService.deleteUserById(user.getId());


        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        redirectAttributes.addFlashAttribute("items", itemService.findAllItems());
        redirectAttributes.addFlashAttribute("user_deleted", true);
        return "redirect:/home";
    }
}
