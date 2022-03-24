package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.entity.User;
import ru.flawden.divinitybankspring.entity.Database;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MainController {

    Database database = Database.getInstance();

    @GetMapping("/authorization")
    public String auth() {

        if (database.getAuthUser() == null) {
            return "mainpages/authorization";
        } else {
            return "profile/user-page";
        }

    }

    //GetMapping, PostMapping
    @PostMapping("/auth-verification")
    public String authVerification(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   Model model) {
        database.deserializeDatabase();

        for (User user: database.users) {
            if (user.geteMail().equals(email) && user.getPassword().equals(password)) {
                database.setAuthUser(user);
                System.out.println(database.getAuthUser());
                model.addAttribute("balance" , user.getBalance() + "$");
                model.addAttribute("names" , user.getFirstName() + " " + user.getLastName());
                return "redirect:/user-page";
            }
        }
        return "mainpages/authorization";
    }

    @GetMapping("/registration")
    public String registration() {
        return "mainpages/registration";
    }

    @PostMapping("/reg-verification")
    public String regVerification(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "lastname") String lastname,
                                  @RequestParam(value = "gender") String gender,
                                  @RequestParam(value = "birdthdate") String birdthdate,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password,
                                  Model model) {
        System.out.println(email + " " + password + " " + name + " " + lastname + " " + gender + " " + birdthdate);
        database.deserializeDatabase();
        for (User user: database.users) {
            if(user.geteMail().equals(email)) {
                model.addAttribute("errorReg", "User with email address " + email + " already exists");
                return "mainpages/registration";
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(birdthdate);
        } catch (ParseException e) {
            System.out.println("Incorrect date format");
        }

        boolean userGender = gender.equals("male");

        database.users.add(new User(name, lastname, email, password, date, userGender));

        database.serializeDatabase();
        return "redirect:/authorization";
    }

}
