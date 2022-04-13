package ru.flawden.divinitybankspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.entity.UserEntity;

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
        if (userDAO.authUser != null) {
            return "redirect:/users/" + userDAO.authUser.getId();
        }
        return "/mainpages/authorization";
    }

    @PostMapping()
    public String authVer(@ModelAttribute("userDTO") UserDTO userDTO) {
        if (userDTO.checkAuth(userDAO, userDTO)) {
            return "redirect:/users/" + userDAO.authUser.getId();
        } else {
            return "/mainpages/authorization";
        }

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        UserEntity user = userDAO.show(id);
        System.out.println(user + " AND " + userDAO.authUser);
        if (!userDAO.authUser.equals(user)) {
            System.out.println("Ошибка авторизации");
            return "redirect:/users";
        }
        System.out.println("Авторизация прошла");
        model.addAttribute("User", user);
        return "/profile/user-page";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        UserEntity user = userDAO.show(id);
        if (!userDAO.authUser.equals(user)) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        System.out.println("Get edit отправлен");
        return "/mainpages/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("user") UserEntity user, @PathVariable("id") int id, Model model) {
        userDAO.update(id, user);
        return "redirect:/users/" + userDAO.authUser.getId();
    }

    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") UserEntity user) {
        return "mainpages/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") UserEntity user) {
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
