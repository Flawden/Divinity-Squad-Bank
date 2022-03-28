package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.User;

@Controller
public class UserPageController {

    private final UserDAO userDAO;

    @Autowired
    public UserPageController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/exit")
    public String exit() {
        userDAO.authUser = null;
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String returnProfile(Model model) {
        User user = userDAO.authUser;
        if (user == null) {
            return "redirect:/authorization";
        } else {
            model.addAttribute("User" , user);
        }
        return "profile/profile";
    }

    @GetMapping("/user-page")
    public String returnUserPage(Model model) {
        User user = userDAO.authUser;
        if (user == null) {
            return "redirect:/authorization";
        } else {
              model.addAttribute("User", user);
        }
        return "profile/user-page";
    }

}
