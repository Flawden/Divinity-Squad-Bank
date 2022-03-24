package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.entity.*;
import ru.flawden.divinitybankspring.entity.util.LoanUtil;

import java.util.Date;

@Controller
public class LoanController {

    Database database = Database.getInstance();
    LoanUtil creditCreator = new LoanUtil();

    @GetMapping("/loan")
    public String returnLoan(Model model) {
        User user = database.getAuthUser();

        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("loan" , user.getLoanList());
        }

        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoan(Model model) {
        User user = database.getAuthUser();

        if (user == null) {
            return "mainpages/authorization";
        } else {
            String loan = user.getLoanList().toString();
            System.out.println(loan);
            model.addAttribute("loan" , loan);
        }

        return "profile/loan/create-loan";
    }

    @PostMapping("/create-loan-ver")
    public String createLoan(@RequestParam(value = "product") String product,
                             @RequestParam(value = "loanterm") String loanterm,
                             @RequestParam(value = "loanamount") String loanamount) {
        database.deserializeDatabase();
        User user = database.getAuthUser();
        int interestRate = creditCreator.getInterestRate(product);

        if (user == null) {
            return "redirect:/authorization";
        } else {
            int loanAmount = Integer.parseInt(loanamount);
            int loanTerm = Integer.parseInt(loanterm);
            double totalSum = creditCreator.calculateCreditSum(loanAmount, loanTerm, interestRate);
            double sumPerMonth = creditCreator.calculateSumPerMonth(totalSum, loanTerm);
            user.getLoanList().add(new Loan(new Date(), totalSum, interestRate, sumPerMonth, loanTerm));
        }
        database.serializeDatabase();
        System.out.println(user.getLoanList());
        return "redirect:/user-page";
    }

}
