package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.dao.DebitCardDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.DebitCard;
import ru.flawden.divinitybankspring.entity.util.DebitCardUtil;
import ru.flawden.divinitybankspring.entity.User;

@Controller
public class DebitController {

    private final UserDAO userDAO;
    private final DebitCardDAO debitCardDAO;
    DebitCardUtil debitCreator;

    @Autowired
    public DebitController(UserDAO userDAO, DebitCardDAO debitCardDAO) {
        this.userDAO = userDAO;
        this.debitCardDAO = debitCardDAO;
        this.debitCreator = new DebitCardUtil(userDAO);
    }

    @GetMapping("/debit-card")
    public String returnDebitCard(Model model) {

        User user = userDAO.authUser;

        if (user == null) {
            return "redirect:/authorization";
        } else {
            userDAO.authUser.setDebitCardList(debitCardDAO.index(user.geteMail()));
            String debit = userDAO.authUser.getDebitCardList().toString();
            model.addAttribute("User" , user);
        }
        return "profile/debit-card";
    }

    @GetMapping("/create-debit")
    public String createDebit(Model model) {
        User user = userDAO.authUser;

        if (user == null) {
            return "mainpages/authorization";
        } else {
            String key = user.geteMail();
            DebitCard debitCard = debitCreator.doDebitCard();
            debitCardDAO.save(debitCard, key);
        }

        return "redirect:/user-page";
    }

}
