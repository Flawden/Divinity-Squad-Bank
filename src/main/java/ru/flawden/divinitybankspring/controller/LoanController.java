package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
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
        User user = userDAO.authUser;

        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("User" , user);
        }

        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoan(Model model) {
        User user = userDAO.authUser;

        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("User" , user);
        }

        return "profile/loan/create-loan";
    }

    //Реквест парамы нахер
    @PostMapping("/create-loan-ver")
    public String createLoan(@RequestParam(value = "product") String product,
                             @RequestParam(value = "loanterm") String loanterm,
                             @RequestParam(value = "loanamount") String loanamount) {
        User user = userDAO.authUser;

        if (user == null) {
            return "redirect:/authorization";
        } else {
            int interestRate = loanUtil.getInterestRate(product);
            int loanAmount = Integer.parseInt(loanamount);
            int loanTerm = Integer.parseInt(loanterm);
            double totalSum = loanUtil.calculateCreditSum(loanAmount, loanTerm, interestRate);
            double sumPerMonth = loanUtil.calculateSumPerMonth(totalSum, loanTerm);
            user.getLoanList().add(new Loan(new Date(), totalSum, interestRate, sumPerMonth, loanTerm));
        }
        return "redirect:/user-page";
    }

}
