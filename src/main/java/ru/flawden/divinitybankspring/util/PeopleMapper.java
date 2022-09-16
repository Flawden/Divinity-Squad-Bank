package ru.flawden.divinitybankspring.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;

@Component
public class PeopleMapper {

    private final ModelMapper mapper;

    public PeopleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Person convertPersonDTOToPerson(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }

    public PersonDTO convertPersonToPersonDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }
}
