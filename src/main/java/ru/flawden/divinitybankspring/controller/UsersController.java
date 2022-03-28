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
        System.out.println(authUtil);
        return "/mainpages/authorization";
    }

    @PostMapping()
    public String authVer(@ModelAttribute("authUtil") AuthUtil authUtil) {
        if (authUtil.checkAuth(userDAO)) {
            return "redirect:/user-page";
        } else {
            return "/mainpages/authorization";
        }

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        System.out.println("New - get");
        return "mainpages/registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        System.out.println("New - post");
        userDAO.save(user);
        return "redirect:/users";
    }

}
