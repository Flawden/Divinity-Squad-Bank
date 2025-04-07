package ru.flawden.divinitybankspring.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.PeopleMapper;
import ru.flawden.divinitybankspring.util.PersonValidator;

/**
 * Controller responsible for handling user registration requests.
 *
 * @author Flawden
 * @version 1.0
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final PeopleService peopleService;
    private final PeopleMapper mapper;
    private final PersonValidator personValidator;

    public RegistrationController(PeopleService peopleService, PeopleMapper mapper, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.mapper = mapper;
        this.personValidator = personValidator;
    }

    /**
     * Displays the registration page.
     *
     * @param personDTO DTO representing the person being registered.
     * @return The name of the view for registration.
     */
    @GetMapping
    public String registationPage(@ModelAttribute("person") PersonDTO personDTO) {
        return "/mainpages/registration";
    }

    /**
     * Processes the registration form submission.
     *
     * @param personDTO DTO containing the person's registration data.
     * @param bindingResult Object to hold validation errors.
     * @return Redirect to the home page if registration is successful, or back to the registration form if there are errors.
     */
    @PostMapping
    public String savePerson(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult) {
        Person person = mapper.convertPersonDTOToPerson(personDTO);
        personValidator.validatePerson(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "mainpages/registration";
        }
        peopleService.save(person);
        return "redirect:/";
    }

}
