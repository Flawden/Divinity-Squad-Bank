package ru.flawden.divinitybankspring.util;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.exception.NonExistentGenderException;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.Optional;

@Component
public class PersonValidator {

    private final PeopleRepository peopleRepository;

    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public void genderValidator(String gender, BindingResult bindingResult) {
       if(!((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("female")))) {
           bindingResult.rejectValue("gender", "", "You have entered a non-existent gender.");
       }
   }

   public void existingEmailValidator(String email, BindingResult bindingResult) {
       Optional<Person> person = peopleRepository.findByEmail(email);
       if(person.isPresent()) {
           if(person.get().getEnabled()) {
               bindingResult.rejectValue("email", "", "User with given email address already exists");
           } else {
               bindingResult.rejectValue("email", "", "This user will be deleted soon. If you are the owner of the account and for some reason decided to save the account - write to the administration");
           }
       }
   }

    public void validatePerson(Person person, BindingResult bindingResult) {
        existingEmailValidator(person.getEmail(), bindingResult);
        genderValidator(person.getGender(), bindingResult);
    }
}
