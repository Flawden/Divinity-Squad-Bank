package ru.flawden.divinitybankspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {

    @GetMapping("/authorization")
    public String sayHello() {
        return "authorization";
    }

}
