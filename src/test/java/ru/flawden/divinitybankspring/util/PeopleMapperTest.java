package ru.flawden.divinitybankspring.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class PeopleMapperTest {

    private PeopleMapper peopleMapper;

    @BeforeEach
    void setup() {
        peopleMapper = new PeopleMapper(new ModelMapper());
    }

    @Test
    void convertPersonDTOToPerson() {

    }
}