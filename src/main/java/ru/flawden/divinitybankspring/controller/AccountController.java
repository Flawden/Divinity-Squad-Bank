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

/**
 * Controller for handling user account-related operations, such as profile updates, deletion, and login management.
 *
 * @author Flawden
 * @version 1.0
 */
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

    /**
     * Displays the home page of the account, showing the user's profile.
     *
     * @param model Holds user information to be displayed in the view.
     * @param principal The logged-in user's principal object.
     * @return The user profile view.
     */
    @GetMapping
    public String homePage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        model.addAttribute("person", person);
        return "profile/user-page";
    }

    /**
     * Displays the profile page.
     *
     * @return The profile view.
     */
    @GetMapping("/profile")
    public String profilePage() {
        return "profile/profile";
    }

    /**
     * Displays the logout confirmation page.
     *
     * @return The logout view.
     */
    @GetMapping("/exit")
    public String logout() {
        return "mainpages/logout";
    }

    /**
     * Displays the account deletion confirmation page.
     *
     * @return The delete account view.
     */
    @GetMapping("/delete")
    public String deleteAccountPage() {
        return "mainpages/delete";
    }

    /**
     * Displays the account update page with the current user's information.
     *
     * @param model Holds user information for the update form.
     * @param principal The logged-in user's principal object.
     * @return The update account view.
     */
    @GetMapping("/update")
    public String updateAccountPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        person.setPassword(null);
        model.addAttribute("person", person);
        return "mainpages/update";
    }

    /**
     * Handles the account update request.
     *
     * @param personDTO The DTO holding the updated user data.
     * @param principal The logged-in user's principal object.
     * @param bindingResult Holds validation errors, if any.
     * @return Redirects to the account home page if successful, otherwise returns the update page.
     */
    @PatchMapping
    public String updateAccount(@ModelAttribute("person") @Valid PersonDTO personDTO, Principal principal, BindingResult bindingResult) {
        Person person = mapper.convertPersonDTOToPerson(personDTO);
        personValidator.genderValidator(person.getGender(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "mainpages/update";
        }
        peopleService.update(principal.getName(), person);
        return "redirect:/account";
    }

    /**
     * Handles account deletion by disabling the account.
     *
     * @param principal The logged-in user's principal object.
     * @return Redirects to the logout page after deletion.
     */
    @DeleteMapping
    public String deleteAccount(Principal principal) {
        peopleService.disableAccount(principal.getName());
        return "redirect:/logout";
    }
}
