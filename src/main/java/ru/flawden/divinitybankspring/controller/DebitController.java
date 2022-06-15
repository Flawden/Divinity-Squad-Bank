package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.dao.DebitCardDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;
import ru.flawden.divinitybankspring.util.DebitCardUtil;

import java.util.List;

@Controller
public class DebitController {

    private final UserDAO userDAO;
    private final DebitCardDAO debitCardDAO;
    private final DebitCardUtil debitCreator;


    public DebitController(UserDAO userDAO, DebitCardDAO debitCardDAO, DebitCardUtil debitCardUtil) {
        this.userDAO = userDAO;
        this.debitCardDAO = debitCardDAO;
        this.debitCreator = debitCardUtil;
    }

    @GetMapping("/users/{id}/debit-card")
    public String returnDebitCard(Model model) {
        UserEntity user = userDAO.authUser;

        List<DebitCardEntity> loanList = debitCardDAO.index(user);
        model.addAttribute("User" , user);
        model.addAttribute("debitCardList", loanList);

        return "profile/debit-card";
    }

    @GetMapping("/create-debit")
    public String createDebit(Model model) {
        UserEntity user = userDAO.authUser;

        DebitCardEntity debitCard = debitCreator.doDebitCard();
        userDAO.addDebitCard(debitCard);

        return "redirect:/users/" + user.getId();
    }

}
