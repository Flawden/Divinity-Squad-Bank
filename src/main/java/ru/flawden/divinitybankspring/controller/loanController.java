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

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class loanController {

    @GetMapping("/loan")
    public String returnLoan(Model model) {
        Database database = Database.getInstance();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            model.addAttribute("loan" , user.getLoanList());
        }
        return "profile/loan";
    }

    @GetMapping("/createloan")
    public String createLoan(Model model) {
        Database database = Database.getInstance();
        User user = database.getAuthUser();
        if (user == null) {
            return "mainpages/authorization";
        } else {
            String loan = user.getLoanList().toString();
            System.out.println(loan);
            model.addAttribute("loan" , loan);
        }
        return "profile/loan/create_loan";
    }

    @RequestMapping(value = "/createLoanVer", method = RequestMethod.POST)
    public String createLoan(@RequestParam(value = "product") String product,
                             @RequestParam(value = "loanterm") String loanterm,
                             @RequestParam(value = "loanamount") String loanamount) {
        Database database = Database.getInstance();
        database.deserializeUsers();
        CreditCreator creditCreator = new CreditCreator();
        User user = database.getAuthUser();
        int interestRate = creditCreator.returnInterestRate(product);
        if (user == null) {
            return "mainpages/authorization";
        } else {
            int loanAmount = Integer.parseInt(loanamount);
            int loanTerm = Integer.parseInt(loanterm);
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("yyyy-MM-dd");
            //Date loanTerm = new Date();
//            try {
//                loanTerm = dateFormat.parse(loanterm);
//            } catch (ParseException e) {
//                System.out.println("Incorrect date format");
//            }

//            int term = (int)creditCreator.returnTermInMonths(loanTerm);

            double totalSum = creditCreator.calculateCreditSum(loanAmount, loanTerm, interestRate);
            double sumPerMonth = creditCreator.calculateSumPerMonth(totalSum, loanTerm);

            user.getLoanList().add(new Loan(new Date(), totalSum, interestRate, sumPerMonth, loanTerm));
        }
        database.serializeUsers(database.users);
        System.out.println(user.getLoanList());
        return "/profile/user_page";
    }

}
