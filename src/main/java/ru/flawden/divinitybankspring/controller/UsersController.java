package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.User;
import ru.flawden.divinitybankspring.entity.util.AuthUtil;

import javax.sound.midi.MidiChannel;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("authUtil") AuthUtil authUtil) {
        return "/mainpages/authorization";
    }

    @PostMapping()
    public String authVer(@ModelAttribute("authUtil") AuthUtil authUtil) {
        if (authUtil.checkAuth(userDAO)) {
            return "redirect:/users/" + userDAO.authUser.getId();
        } else {
            return "/mainpages/authorization";
        }

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        if (userDAO.authUser != userDAO.show(id)) {
            return "redirect:/users";
        }
        model.addAttribute("User", userDAO.show(id));
        return "/profile/user-page";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "mainpages/registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users";
    }

}
