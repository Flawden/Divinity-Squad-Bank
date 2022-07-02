package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.DebitCardDAO;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.entity.Role;
import ru.flawden.divinitybankspring.entity.UserEntity;
import ru.flawden.divinitybankspring.service.UserService;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final DebitCardDAO debitCardDAO;
    private final LoanDAO loanDAO;
    private final UserService userService;

    @Autowired
    public UsersController(DebitCardDAO debitCardDAO, LoanDAO loanDAO, UserService userService) {
        this.debitCardDAO = debitCardDAO;
        this.loanDAO = loanDAO;
        this.userService = userService;
    }

    @GetMapping()
    public String homePage(Model model, Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        model.addAttribute("debitCardList", debitCardDAO.getTwoDebitCard(user));
        model.addAttribute("User", user);
        model.addAttribute("loanList", loanDAO.getTwoLoan(user));
        return "/profile/user-page";
    }

    @GetMapping("/profile")
    public String show(Principal principal, Model model) {
        UserEntity user = userService.findByEmail(principal.getName());
        model.addAttribute("User", user);
        return "/profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "/mainpages/edit";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute UserEntity user, Principal principal) {
        UserEntity userForUpdate = userService.findByEmail(principal.getName());
        userService.update(userForUpdate.getEmail(), user);
        return "redirect:/users";
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") UserEntity user) {
        return "mainpages/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") UserEntity user) {
        user.setEnabled(true);;
        user = userService.save(user);
        user.setRoles(Collections.singleton(Role.USER));
        userService.update(user.getEmail(), user);
        return "redirect:/users/login";
    }

    @DeleteMapping()
    public String delete(Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        userService.delete(user.getEmail());
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
