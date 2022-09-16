package ru.flawden.divinitybankspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.PeopleMapper;
import ru.flawden.divinitybankspring.util.PersonValidator;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AccountControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        PeopleService peopleService = mock(PeopleService.class);
        PeopleMapper peopleMapper = mock(PeopleMapper.class);
        PersonValidator personValidator = mock(PersonValidator.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(peopleService, peopleMapper, personValidator)).build();
    }

    @Test
    public void homePage() {

    }

    @Test
    public void profilePage() throws Exception {
        this.mockMvc.perform(get("/account/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profile"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void logout() throws Exception {
        this.mockMvc.perform(get("/account/exit"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainpages/logout"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void deleteAccountPage() throws Exception {
        this.mockMvc.perform(get("/account/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainpages/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateAccountPage(){
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void deleteAccount() {
    }
}