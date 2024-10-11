package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.Optional;

/**
 * Validator class for validating Person objects.
 *
 * @author Flawden
 * @version 1.0
 */
@Component
public class PersonValidator {

    private final PeopleRepository peopleRepository;

    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    /**
     * Validates the gender of a person.
     *
     * @param gender        the gender to validate.
     * @param bindingResult the result object to hold validation errors.
     */
    public void genderValidator(String gender, BindingResult bindingResult) {
        if (!((gender.equalsIgnoreCase("male")) || (gender.equalsIgnoreCase("female")))) {
            bindingResult.rejectValue("gender", "", "You have entered a non-existent gender.");
        }
    }

    /**
     * Validates if the email already exists in the repository.
     *
     * @param email        the email to validate.
     * @param bindingResult the result object to hold validation errors.
     */
    public void existingEmailValidator(String email, BindingResult bindingResult) {
        Optional<Person> person = peopleRepository.findByEmail(email);
        if (person.isPresent()) {
            if (person.get().getEnabled()) {
                bindingResult.rejectValue("email", "", "User with given email address already exists");
            } else {
                bindingResult.rejectValue("email", "", "This user will be deleted soon. If you are the owner of the account and for some reason decided to save the account - write to the administration");
            }
        }
    }

    /**
     * Validates a Person object by checking email and gender.
     *
     * @param person       the Person object to validate.
     * @param bindingResult the result object to hold validation errors.
     */
    public void validatePerson(Person person, BindingResult bindingResult) {
        existingEmailValidator(person.getEmail(), bindingResult);
        genderValidator(person.getGender(), bindingResult);
    }
}
