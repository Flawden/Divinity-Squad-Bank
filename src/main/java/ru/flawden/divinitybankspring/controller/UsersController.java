package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.DebitCardDAO;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.dto.UserDetailsDTO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.Role;
import ru.flawden.divinitybankspring.entity.UserEntity;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {


    //Автовайрить серис, а в них DAO
    private final UserDAO userDAO;
    private final DebitCardDAO debitCardDAO;
    private final LoanDAO loanDAO;

    @Autowired
    public UsersController(UserDAO userDAO, DebitCardDAO debitCardDAO, LoanDAO loanDAO) {
        this.userDAO = userDAO;
        this.debitCardDAO = debitCardDAO;
        this.loanDAO = loanDAO;
    }

    @GetMapping()
    public String homePage(Model model, Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        System.out.println(principal.getName());
        model.addAttribute("debitCardList", debitCardDAO.getTwoDebitCard(user));
        model.addAttribute("User", user);
        model.addAttribute("loanList", loanDAO.getTwoLoan(user));
        return "/profile/user-page";
    }

    @GetMapping("/profile")
    public String show(Principal principal, Model model) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        model.addAttribute("User", user);
        return "/profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "/mainpages/edit";
    }

    @PatchMapping("/edit")
    public String update(Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        userDAO.update(user.getId(), user);
        return "redirect:/users/";
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") UserEntity user) {
        return "mainpages/registration";
    }



    @PostMapping("/registration")
    public String create(@ModelAttribute("user") UserEntity user) {
        user.setEnabled(true);;
        user = userDAO.save(user);
        user.setRoles(Collections.singleton(Role.USER));
        userDAO.update(user.getId(), user);
        return "redirect:/users/login";
    }

    @DeleteMapping("/")
    public String delete(Principal principal) {
        UserEntity user = userDAO.findByEmail(principal.getName());
        userDAO.delete(user.getId());
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String LoginUser() {
        return "mainpages/authorization";
    }

    @GetMapping("/exit")
    public String exit() {
        return "redirect:/logout";
    }

}
