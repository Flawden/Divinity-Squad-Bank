package ru.flawden.divinitybankspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.repository.PeopleRepository;
import ru.flawden.divinitybankspring.security.PersonDetails;

@Service
public class PersonDetailService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new PersonDetails(peopleRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting to load user by username/email: " + username);
        Person person = peopleRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        System.out.println("User found: " + person.getFullName());
        return new PersonDetails(person);
    }

}
