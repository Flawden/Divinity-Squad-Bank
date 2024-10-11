package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Controller for handling user login and authentication requests.
 *
 * @author Flawden
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Displays the login page and handles login and logout actions.
     *
     * @param error  Optional parameter indicating if there was an error during login.
     * @param login  Optional parameter indicating if the user has logged out.
     * @param model  Model to pass attributes to the view.
     * @param principal The authenticated user, if present.
     * @return Redirects to the account page if the user is already authenticated, otherwise returns the login page.
     */
    @GetMapping
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String login,
                            Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/account";
        }
        model.addAttribute("error", error != null);
        model.addAttribute("logout", login != null);
        return "mainpages/authorization";
    }

}
