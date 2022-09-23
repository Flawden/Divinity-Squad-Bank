package ru.flawden.divinitybankspring.util;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleMapperTest {

    private PeopleMapper peopleMapper;
    private final Person testPerson = new Person("Firstname", "Surname", "Male", new Date(), "email@email.ru", "password", true);
    private final PersonDTO personDTO = new PersonDTO();

    @Before
    public void init() {
        personDTO.setGender("Male");
        personDTO.setEmail("email.test");
        personDTO.setFirstname("Firstname");
        personDTO.setSurname("Surname");
        personDTO.setPassword("password");
        personDTO.setDateOfBirth(new Date());
    }

    @BeforeEach
    public void setup() {
        peopleMapper = new PeopleMapper(new ModelMapper());
    }

    @Test
    public void convertPersonDTOToPerson() {

    }
}