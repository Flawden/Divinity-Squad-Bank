package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userPageController {

    @GetMapping("/loanlist")
    public String auth() {

        return "Ты нехороший";
    }

}
