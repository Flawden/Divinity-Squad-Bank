package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.*;
import ru.flawden.divinitybankspring.service.LoanService;
import ru.flawden.divinitybankspring.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoanController {
    private final LoanService loanService;
    private final UserService userService;
    private final UserDAO userDAO;
    private final LoanDAO loanDAO;

    @Autowired
    public LoanController(LoanService loanService, UserService userService, UserDAO userDAO, LoanDAO loanDAO) {
        this.loanService = loanService;
        this.userService = userService;
        this.userDAO = userDAO;
        this.loanDAO = loanDAO;
    }


    @GetMapping("users/loan")
    public String returnLoan(Model model, Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        model.addAttribute("User" , user);
        model.addAttribute("loanList", user.getLoanList());
        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoanForm(@ModelAttribute("loanDTO") LoanDTO loanDTO, Model model) {
        List<String> options = new ArrayList<>();
        List<LoanOffer> loanOffers = loanDAO.getAllLoanOffer();
        loanOffers.forEach(offer -> options.add(offer.getCreditName()));
        model.addAttribute("options", options);
        return "profile/loan/create-loan";
    }

    @PostMapping("/create-loan")
    public String createLoan(@ModelAttribute("loan") LoanDTO loanDTO, Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        LoanEntity loan = loanService.doLoan(loanDTO);;
        loanService.addLoan(loan, user);
        return "redirect:/users/";
    }

}
