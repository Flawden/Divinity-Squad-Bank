package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String login,
                            Model model, Principal principal) {
        if(principal != null) {
            return "redirect:/account";
        }
        model.addAttribute("error", error!=null);
        model.addAttribute("logout", login!=null);
        return "mainpages/authorization";
    }

}
