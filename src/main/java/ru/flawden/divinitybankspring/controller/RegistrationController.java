package ru.flawden.divinitybankspring.controller;

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

import javax.validation.Valid;

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

    @GetMapping
    public String registationPage(@ModelAttribute("person") PersonDTO personDTO) {
        return "mainpages/registration";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult) {
        Person person = mapper.convertPersonDTOToPerson(personDTO);
        personValidator.validatePerson(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "mainpages/registration";
        }
        peopleService.save(person);
        return "redirect:/index";
    }

}
