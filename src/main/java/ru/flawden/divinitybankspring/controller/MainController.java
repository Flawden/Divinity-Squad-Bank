package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/mainpages/logout";
    }

}
