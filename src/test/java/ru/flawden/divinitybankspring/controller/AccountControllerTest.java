package ru.flawden.divinitybankspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import ru.flawden.divinitybankspring.dto.PersonDTO;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.security.PersonDetails;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.PeopleMapper;
import ru.flawden.divinitybankspring.util.PersonValidator;

import java.security.Principal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class AccountControllerTest {

    private MockMvc mockMvc;
    private PersonValidator personValidator;
    private PeopleMapper peopleMapper;

    private AccountController accountController;
    private final Person testPerson = new Person("Firstname", "Surname", "Male", new Date(), "email@email.ru", "password", true);

    private final UserDetails testUserDetail = new PersonDetails(testPerson);

    private BindingResult bindingResult;
    @Before
    public void setup() {
        PeopleService peopleService = mock(PeopleService.class);
        peopleMapper = mock(PeopleMapper.class);
        personValidator = mock(PersonValidator.class);
        accountController = new AccountController(peopleService, peopleMapper, personValidator);
        bindingResult = mock(BindingResult.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void homePage() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/account").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/user-page"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
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
    public void updateAccountPage() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/account").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/user-page"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateAccount() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(peopleMapper.convertPersonDTOToPerson(any(PersonDTO.class))).thenReturn(testPerson);
        this.mockMvc.perform(patch("/account").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/account"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateAccountRequestRedirectUserToMainpageIfRegistrationNotValid() {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(peopleMapper.convertPersonDTOToPerson(any(PersonDTO.class))).thenReturn(testPerson);
        when(bindingResult.hasErrors()).thenReturn(true);
        String view = accountController.updateAccount(new PersonDTO(), mockPrincipal, bindingResult);
        Assertions.assertEquals("mainpages/update" ,view );
    }

    @Test
    public void deleteAccount() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(delete("/account").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/logout"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}