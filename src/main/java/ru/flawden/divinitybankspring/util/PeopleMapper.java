package ru.flawden.divinitybankspring.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;

/**
 * Utility class for mapping between Person and PersonDTO objects.
 *
 * @author Flawden
 * @version 1.0
 */
@Component
public class PeopleMapper {

    private final ModelMapper mapper;

    public PeopleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Converts a PersonDTO object to a Person entity.
     *
     * @param personDTO the data transfer object to convert.
     * @return the converted Person entity.
     */
    public Person convertPersonDTOToPerson(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }

    /**
     * Converts a Person entity to a PersonDTO object.
     *
     * @param person the Person entity to convert.
     * @return the converted PersonDTO object.
     */
    public PersonDTO convertPersonToPersonDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }
}
