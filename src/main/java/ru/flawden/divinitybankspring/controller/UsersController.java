package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.DebitCardDAO;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.Role;
import ru.flawden.divinitybankspring.entity.UserEntity;

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
    public String loginPage() {
        return "/profile/profile";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        UserEntity user = userDAO.show(id);
        if (!userDAO.authUser.equals(user)) {
            return "redirect:/users";
        }
        model.addAttribute("debitCardList", debitCardDAO.getTwoDebitCard(user));
        model.addAttribute("User", user);
        model.addAttribute("balance", debitCardDAO.getBalance(user));
        model.addAttribute("loanList", loanDAO.getTwoLoan(user));
        return "/profile/user-page";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        UserEntity user = userDAO.show(id);
        if (!userDAO.authUser.equals(user)) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "/mainpages/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("user") UserEntity user, @PathVariable("id") Long id, Model model) {
        userDAO.update(id, user);
        return "redirect:/users/" + userDAO.authUser.getId();
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

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDAO.delete(id);
        userDAO.authUser = null;
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String LoginUser() {
        return "mainpages/authorization";
    }

    @GetMapping("{id}/profile")
    public String returnProfile(Model model) {
        UserEntity user = userDAO.authUser;
        if (user == null) {
            return "redirect:/authorization";
        } else {
            model.addAttribute("User" , user);
        }
        return "profile/profile";
    }

    @GetMapping("/exit")
    public String exit() {
        userDAO.authUser = null;
        return "redirect:/users";
    }

}
