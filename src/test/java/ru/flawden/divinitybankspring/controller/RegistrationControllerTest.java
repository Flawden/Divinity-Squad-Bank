package ru.flawden.divinitybankspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.PeopleMapper;
import ru.flawden.divinitybankspring.util.PersonValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        PeopleService peopleService = mock(PeopleService.class);
        PeopleMapper peopleMapper = mock(PeopleMapper.class);
        PersonValidator personValidator = mock(PersonValidator.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new RegistrationController(peopleService, peopleMapper, personValidator)).build();
    }

    @Test
    public void registationPage() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("/mainpages/registration"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void savePerson() throws Exception {
        this.mockMvc.perform(post("/registration"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}