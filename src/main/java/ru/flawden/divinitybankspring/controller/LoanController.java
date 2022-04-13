package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.*;
import ru.flawden.divinitybankspring.util.LoanUtil;

import java.util.Date;

@Controller
public class LoanController {

    LoanUtil loanUtil = new LoanUtil();
    private final UserDAO userDAO;
    private final LoanDAO loanDAO;

    @Autowired
    public LoanController(UserDAO userDAO, LoanDAO loanDAO) {
        this.userDAO = userDAO;
        this.loanDAO = loanDAO;
    }


    @GetMapping("users/{id}/loan")
    public String returnLoan(Model model) {
        UserEntity user = userDAO.authUser;

        if (user == null) {
            return "mainpages/authorization";
        } else {
            userDAO.authUser.setLoanList(loanDAO.index(user));
            model.addAttribute("User" , user);
        }

        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoanForm(@ModelAttribute("loanDTO") LoanDTO loanDTO) {
        UserEntity user = userDAO.authUser;
        if (user == null) {
            return "mainpages/authorization";
        } else {
            return "profile/loan/create-loan";
        }
    }

    //Реквест парамы нахер
    @PostMapping("/create-loan")
    public String createLoan(@ModelAttribute("loan") LoanDTO loanDTO) {
        UserEntity user = userDAO.authUser;
        if (user == null) {
            return "redirect:/authorization";
        } else {
              LoanEntity loan = loanUtil.doLoan(loanDTO);
              userDAO.addLoan(loan);
            return "redirect:/users/" + userDAO.authUser.getId();
        }
    }

}
