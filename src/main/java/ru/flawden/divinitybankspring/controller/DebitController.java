package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.entity.Database;
import ru.flawden.divinitybankspring.entity.util.DebitCardUtil;
import ru.flawden.divinitybankspring.entity.User;

@Controller
public class DebitController {

    Database database = Database.getInstance();
    DebitCardUtil debitCreator = new DebitCardUtil();

    @GetMapping("/debit-card")
    public String returnDebitCard(Model model) {

        User user = database.getAuthUser();

        if (user == null) {
            return "redirect:/authorization";
        } else {
            String debit = database.getAuthUser().getDebitCardList().toString();
            model.addAttribute("User" , user);
        }
        return "profile/debit-card";
    }

    @GetMapping("/create-debit")
    public String createDebit(Model model) {
        database.deserializeDatabase();
        User user = database.getAuthUser();

        if (user == null) {
            return "mainpages/authorization";
        } else {
            debitCreator.doDebitCard();
            database.serializeDatabase();
        }

        return "redirect:/user-page";
    }

}
