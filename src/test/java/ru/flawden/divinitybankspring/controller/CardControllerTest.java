package ru.flawden.divinitybankspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.security.PersonDetails;
import ru.flawden.divinitybankspring.service.CardsService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.Date;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CardControllerTest {

    private MockMvc mockMvc;

    private final Person testPerson = new Person("Firstname", "Surname", "Male", new Date(), "email@email.ru", "password", true);

    private final UserDetails testUserDetail = new PersonDetails(testPerson);

    @Before
    public void setup() {
        CardsService cardsService = mock(CardsService.class);
        PeopleService peopleService = mock(PeopleService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CardController(cardsService, peopleService)).build();
    }

    @Test
    public void getCardListPage() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/cards").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/cards"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void selectCard() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/cards/select-card").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/card/select-card"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void createDebitCard() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/cards/create-debit-card").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/account"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}