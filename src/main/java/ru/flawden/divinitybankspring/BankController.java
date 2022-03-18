package ru.flawden.divinitybankspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.flawden.divinitybankspring.user_objects.Database;
import ru.flawden.divinitybankspring.user_objects.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class BankController {

    @GetMapping("/authorization")
    public String auth() {

        return "mainpages/authorization";
    }

    @GetMapping("/registration")
    public String registration() {
        return "mainpages/registration";
    }

    @RequestMapping(value = "/authVerification", method = RequestMethod.POST)
    public String authVerification(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password) {
        Database database = Database.getInstance();
        database.deserializeUsers();

        for (User user: database.users) {
            if (user.geteMail().equals(email) && user.getPassword().equals(password)) {
                return "mainpages/user_page";
            } else {
                System.out.println("Ворюга ты сраная");
            }
        }

        return "mainpages/authorization";
    }

    @GetMapping("/user_page")
    public String userPage() {

        return "mainpages/user_page";
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
            System.out.println("Все наебнулось");
        }
        database.users.add(new User(name, lastname, email, password, date, true));

        System.out.println(birdthdate);
        System.out.println(database.users);

        database.serializeUsers(database.users);
        return "mainpages/registration";
    }

}
