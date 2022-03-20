package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.entity.Database;
import ru.flawden.divinitybankspring.entity.DebitCreator;
import ru.flawden.divinitybankspring.entity.User;

@Controller
public class debitController {

    @GetMapping("/debit_card")
    public String returnDebitCard(Model model) {
        Database database = Database.getInstance();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            String debit = database.getAuthUser().getDebitCardList().toString();
            model.addAttribute("debit" , debit);
        }
        return "profile/debit_card";
    }

    @GetMapping("/createDebit")
    public String createDebit(Model model) {
        Database database = Database.getInstance();
        database.deserializeUsers();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            DebitCreator debitCreator = new DebitCreator();
            debitCreator.doDebitCard();
            database.serializeUsers(database.users);
            model.addAttribute("balance" , user.getBalance() + "$");
            model.addAttribute("names" , user.getFirstName() + " " + user.getLastName());
        }

        return "/profile/user_page";
    }

}
