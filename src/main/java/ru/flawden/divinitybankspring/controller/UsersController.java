package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.entity.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("userDTO") UserDTO userDTO) {
        return "/mainpages/authorization";
    }

    @PostMapping()
    public String authVer(@ModelAttribute("userDTO") UserDTO userDTO) {
        if (userDTO.checkAuth(userDAO)) {
            return "redirect:/users/" + userDAO.authUser.getId();
        } else {
            return "/mainpages/authorization";
        }

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        if (!userDAO.authUser.equals(userDAO.show(id))) {
            return "redirect:/users";
        }
        model.addAttribute("User", userDAO.show(id));
        return "/profile/user-page";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userDAO.show(id);
        if (!userDAO.authUser.equals(user)) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        System.out.println("Get edit отправлен");
        return "/mainpages/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id, Model model) {
        userDAO.update(id, user);
        return "redirect:/users/" + userDAO.authUser.getId();
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {
        return "mainpages/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userDAO.delete(id);
        return "redirect:/users";
    }

    @GetMapping("{id}/profile")
    public String returnProfile(Model model) {
        User user = userDAO.authUser;
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
