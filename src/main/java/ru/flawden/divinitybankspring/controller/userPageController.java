package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.flawden.divinitybankspring.entity.CreditCreator;
import ru.flawden.divinitybankspring.entity.Database;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.User;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class userPageController {

    @GetMapping("/exit")
    public String exit() {
        Database database = Database.getInstance();
        database.setAuthUser(null);
        return "/mainpages/authorization";
    }

    @GetMapping("/profile")
    public String returnProfile(Model model) {
        Database database = Database.getInstance();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("balance" , user.getBalance() + "$");
            model.addAttribute("names" , user.getFirstName() + " " + user.getLastName());
            model.addAttribute("test" , "<h2>Я готов с утром новым жить с начала начинать</h2>");
        }
        return "profile/profile";
    }

    @GetMapping("/user_page")
    public String returnUserPage(Model model) {
        Database database = Database.getInstance();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("balance" , user.getBalance() + "$");
            model.addAttribute("names" , user.getFirstName() + " " + user.getLastName());
        }
        return "profile/user_page";
    }

}
