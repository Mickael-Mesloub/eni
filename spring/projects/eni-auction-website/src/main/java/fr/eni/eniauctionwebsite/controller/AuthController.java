package fr.eni.eniauctionwebsite.controller;

import fr.eni.eniauctionwebsite.bll.UserService;
import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.User;
import fr.eni.eniauctionwebsite.controller.dto.user.UserDTO;
import fr.eni.eniauctionwebsite.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    UserService userService;
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        UserDTO userRegistrationDTO = new UserDTO();
        model.addAttribute("userRegistrationDTO", userRegistrationDTO);

        return "view-register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid @ModelAttribute("userRegistrationDTO") UserDTO userRegistrationDTO,
            BindingResult result
    ) {
        boolean passwordNotEmpty = userRegistrationDTO.getPassword() != null && !userRegistrationDTO.getPassword().isEmpty();
        boolean confirmPasswordNotEmpty = userRegistrationDTO.getConfirmPassword() != null && !userRegistrationDTO.getConfirmPassword().isEmpty();

        boolean passwordsMatch = userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword());

        // Check if passwords fields are filled and if password and confirmPassword match
        if (passwordNotEmpty
                && confirmPasswordNotEmpty
                && !passwordsMatch) {

            // if passwords don't match, put confirmPassword in error
            result.rejectValue(
                    "confirmPassword",
                    "password.mismatch",
                    "Les mots de passe ne correspondent pas"
            );
        }

        // If errors occur with data validation, display view with error messages
        if (result.hasErrors()) {
            return "view-register";
        }
        // Instantiate user
        User newUserEntity = new User();

        // Set 100 credit points by default
        newUserEntity.setCreditPoints(100);

        // Instantiate address for new user
        Address address = new Address(
                userRegistrationDTO.getStreet(),
                userRegistrationDTO.getZipcode(),
                userRegistrationDTO.getCity()
        );

        // Set address with values from fields
        newUserEntity.setAddress(address);

        // Copy values from user DTO to user Entity
        BeanUtils.copyProperties(userRegistrationDTO, newUserEntity);

        try {
            // Try to create new user calling userService
            userService.createUser(newUserEntity);

        } catch (UserAlreadyExistsException ex) {

            // If username already exists, put username field in error
            if (ex.isUsernameExists()) {
                result.rejectValue(
                        "username",
                        "user.username.exists",
                        "Ce pseudo est déjà pris"
                );
            }

            // If email already exists, put email field in error
            if (ex.isEmailExists()) {
                result.rejectValue(
                        "email",
                        "user.email.exists",
                        "Cet email est déjà pris"
                );
            }

            return "view-register";
        }

        return "redirect:/login?reason=registerSuccess";
    }

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(value = "reason", required = false) String reason,
            Model model) {

        String message = "";
        String attributeName = "";

        // Display warning message if email and/or username or password were modified
        if (reason != null) {
            switch (reason) {
                case "usernameAndEmailChanged":
                    message = "Votre pseudo et votre email ont été modifiés. Pour des raisons de sécurité, vous avez été déconnecté(e).";
                    attributeName = "warningMessage";
                    break;
                case "usernameChanged":
                    message = "Votre pseudo a été modifié. Pour des raisons de sécurité, vous avez été déconnecté(e).";
                    attributeName = "warningMessage";
                    break;
                case "emailChanged":
                    message = "Votre email a été modifié. Pour des raisons de sécurité, vous avez été déconnecté(e).";
                    attributeName = "warningMessage";
                    break;
                case "passwordChanged":
                    message = "Votre mot de passe a été modifié. Pour des raisons de sécurité, vous avez été déconnecté(e).";
                    attributeName = "warningMessage";
                    break;
                case "registerSuccess":
                    message = "Votre compte a été créé avec succès ! Vous pouvez maintenant vous connecter.";
                    attributeName = "successMessage";
                    break;
                default:
                    message = "";
            }
        }

        model.addAttribute(
                attributeName,
                message
        );

        return "view-login";
    }

    @GetMapping("/logout")
    public String getLogout() {

        return "view-login";
    }
}
