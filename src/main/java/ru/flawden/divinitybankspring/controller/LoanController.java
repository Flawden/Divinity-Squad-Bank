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


    @GetMapping("users/{id}/loan")
    public String returnLoan(Model model) {
        UserEntity user = userDAO.authUser;

        if (user == null) {
            return "mainpages/authorization";
        } else {
            List<LoanEntity> loanList = loanDAO.index(user);
            model.addAttribute("User" , user);
            model.addAttribute("loanList", loanList); //Убрать и использовать user.getLoanList()
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
