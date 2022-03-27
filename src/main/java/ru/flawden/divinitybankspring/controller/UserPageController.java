package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flawden.divinitybankspring.entity.Database;
import ru.flawden.divinitybankspring.entity.User;

@Controller
public class UserPageController {

    Database database = Database.getInstance();

    @GetMapping("/exit")
    public String exit() {
        database.serializeDatabase();
        database.setAuthUser(null);
        return "/mainpages/authorization";
    }

    @GetMapping("/profile")
    public String returnProfile(Model model) {
        User user = database.getAuthUser();
        if (user == null) {
            return "redirect:/authorization";
        } else {
            model.addAttribute("User" , user);
        }
        return "profile/profile";
    }

    @GetMapping("/user-page")
    public String returnUserPage(Model model) {
        User user = database.getAuthUser();
        if (user == null) {
            return "redirect:/authorization";
        } else {
              model.addAttribute("User", user);
        }
        System.out.println(user);
        return "profile/user-page";
    }

}
