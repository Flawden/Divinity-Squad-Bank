package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.flawden.divinitybankspring.entity.User;
import ru.flawden.divinitybankspring.entity.Database;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class mainController {

    @GetMapping("/authorization")
    public String auth() {

        Database database = Database.getInstance();
        if (database.getAuthUser() == null) {
            return "mainpages/authorization";
        } else {
            return "profile/user_page";
        }

    }

    @GetMapping("/registration")
    public String registration() {
        return "mainpages/registration";
    }

    @RequestMapping(value = "/authVerification", method = RequestMethod.POST)
    public String authVerification(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   Model model) {
        Database database = Database.getInstance();
        database.deserializeUsers();

        for (User user: database.users) {
            if (user.geteMail().equals(email) && user.getPassword().equals(password)) {
                database.setAuthUser(user);
                System.out.println(database.getAuthUser());
                model.addAttribute("balance" , user.getBalance() + "$");
                model.addAttribute("names" , user.getFirstName() + " " + user.getLastName());
                return "profile/user_page";
            }
        }

        return "mainpages/authorization";
    }

    @RequestMapping(value = "/regVerification", method = RequestMethod.POST)
    public String regVerification(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "lastname") String lastname,
                                  @RequestParam(value = "gender") String gender,
                                  @RequestParam(value = "birdthdate") String birdthdate,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password) {
        System.out.println(email + " " + password + " " + name + " " + lastname + " " + gender + " " + birdthdate);

        Database database = Database.getInstance();
        database.deserializeUsers();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(birdthdate);
        } catch (ParseException e) {
            System.out.println("Incorrect date format");
        }
        database.users.add(new User(name, lastname, email, password, date, true));

        System.out.println(birdthdate);
        System.out.println(database.users);

        database.serializeUsers(database.users);
        return "mainpages/registration";
    }

}
