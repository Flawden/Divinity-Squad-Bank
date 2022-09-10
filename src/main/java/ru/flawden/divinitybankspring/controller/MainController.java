package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.service.PeopleService;

@Controller
public class MainController {

    private final PeopleService peopleService;

    public MainController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/mainpages/logout";
    }

}
