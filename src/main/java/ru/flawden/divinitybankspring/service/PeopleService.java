package ru.flawden.divinitybankspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.Role;
import ru.flawden.divinitybankspring.exception.UserNotFoundException;
import ru.flawden.divinitybankspring.repository.CardsRepository;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder encoder;

    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder encoder) {
        this.peopleRepository = peopleRepository;
        this.encoder = encoder;
    }

    public Person findByEmail(String email) {
        return peopleRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public void save(Person person) {
        person.setEnabled(true);
        person.setRoles(Collections.singleton(Role.USER));
        person.setPassword(encoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    @Transactional
    public void disableAccount(String email) {
        Person person = findByEmail(email);
        person.setEnabled(false);
        peopleRepository.save(person);
    }

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
