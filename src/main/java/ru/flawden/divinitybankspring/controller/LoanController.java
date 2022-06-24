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

import java.security.Principal;
import java.util.List;

@Controller
public class LoanController {

    private final LoanUtil loanUtil;
    private final UserDAO userDAO;
    private final LoanDAO loanDAO;

    @Autowired
    public LoanController(UserDAO userDAO, LoanDAO loanDAO) {
        this.userDAO = userDAO;
        this.loanDAO = loanDAO;
        this.loanUtil = new LoanUtil();
    }


    @GetMapping("users/loan")
    public String returnLoan(Model model, Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        List<LoanEntity> loanList = loanDAO.index(user);
        System.out.println(loanList.size());
        model.addAttribute("User" , user);
        model.addAttribute("loanList", loanList); //Убрать и использовать user.getLoanList()
        return "profile/loan";
    }

    @GetMapping("/create-loan")
    public String createLoanForm(@ModelAttribute("loanDTO") LoanDTO loanDTO, Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        return "profile/loan/create-loan";
    }

    @PostMapping("/create-loan")
    public String createLoan(@ModelAttribute("loan") LoanDTO loanDTO, Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        LoanEntity loan = loanUtil.doLoan(loanDTO);
        userDAO.addLoan(loan, user);
        return "redirect:/users/";
    }

}
