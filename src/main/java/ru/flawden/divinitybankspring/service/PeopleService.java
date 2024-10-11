package ru.flawden.divinitybankspring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.Role;
import ru.flawden.divinitybankspring.exception.UserNotFoundException;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.Collections;

/**
 * Service class for managing user accounts in the banking application.
 * Provides methods to find, save, disable, and update user accounts.
 *
 * @author Flawden
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder encoder;

    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder encoder) {
        this.peopleRepository = peopleRepository;
        this.encoder = encoder;
    }

    /**
     * Finds a person by their email address.
     *
     * @param email the email of the person to be found.
     * @return the Person entity associated with the provided email.
     * @throws UserNotFoundException if no user is found with the given email.
     */
    public Person findByEmail(String email) {
        return peopleRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * Saves a new person to the repository.
     * Sets the person as enabled, assigns the user role, and encodes the password.
     *
     * @param person the Person entity to be saved.
     */
    @Transactional
    public void save(Person person) {
        person.setEnabled(true);
        person.setRoles(Collections.singleton(Role.USER));
        person.setPassword(encoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    /**
     * Disables a user's account based on their email address.
     *
     * @param email the email of the person whose account is to be disabled.
     */
    @Transactional
    public void disableAccount(String email) {
        Person person = findByEmail(email);
        person.setEnabled(false);
        peopleRepository.save(person);
    }

    /**
     * Updates a person's information based on the provided email and updated Person entity.
     *
     * @param email          the email of the person to be updated.
     * @param updatedPerson  the updated Person entity with new information.
     */
    @Transactional
    public void update(String email, Person updatedPerson) {
        Person person = findByEmail(email);
        person.setEmail(updatedPerson.getEmail());
        person.setFirstname(updatedPerson.getFirstname());
        person.setSurname(updatedPerson.getSurname());
        person.setPassword(encoder.encode(updatedPerson.getPassword()));
        person.setDateOfBirth(updatedPerson.getDateOfBirth());
        person.setGender(updatedPerson.getGender());
        peopleRepository.save(person);
    }

}
