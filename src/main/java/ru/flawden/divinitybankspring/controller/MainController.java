package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for handling the root and logout requests.
 *
 * @author Flawden
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * Displays the home page (index).
     *
     * @return The name of the view representing the index page.
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * Handles the logout request and displays the logout page.
     *
     * @return The name of the view representing the logout page.
     */
    @GetMapping("/logout")
    public String logout() {
        return "/mainpages/logout";
    }

}
