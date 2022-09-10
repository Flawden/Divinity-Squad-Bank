package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.PeopleMapper;
import ru.flawden.divinitybankspring.util.PersonValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final PeopleService peopleService;
    private final PeopleMapper mapper;
    private final PersonValidator personValidator;

    public AccountController(PeopleService peopleService, PeopleMapper mapper, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.mapper = mapper;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String homePage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        model.addAttribute("person", person);
        return "profile/user-page";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile/profile";
    }

    @GetMapping("/exit")
    public String logout() {
        return "mainpages/logout";
    }

    @GetMapping("/delete")
    public String deleteAccountPage() {
        return "mainpages/delete";
    }

    @GetMapping("/update")
    public String updateAccountPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        person.setPassword(null);
        model.addAttribute("person", person);
        return "mainpages/update";
    }

    @PatchMapping
    public String updateAccount(@ModelAttribute("person") @Valid PersonDTO personDTO, Principal principal, BindingResult bindingResult) {
        Person person = mapper.convertPersonDTOToPerson(personDTO);
        personValidator.genderValidator(person.getGender(), bindingResult);
        if(bindingResult.hasErrors()) {
            return "mainpages/registration";
        }
        peopleService.update(principal.getName(), person);
        return "redirect:/account";
    }

    @DeleteMapping
    public String deleteAccount(Principal principal) {
        peopleService.disableAccount(principal.getName());
        return "redirect:/logout";
    }
}
