package ru.flawden.divinitybankspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.card.CreditCard;
import ru.flawden.divinitybankspring.security.PersonDetails;
import ru.flawden.divinitybankspring.service.CardsService;
import ru.flawden.divinitybankspring.service.LoanOfferService;
import ru.flawden.divinitybankspring.service.LoanService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoanControllerTest {

    private MockMvc mockMvc;
    private LoanService loanService;

    private final Person testPerson = new Person("Firstname", "Surname", "Male", new Date(), "email@email.ru", "password", true);

    private final UserDetails testUserDetail = new PersonDetails(testPerson);

    @Before
    public void setup() {
        loanService = mock(LoanService.class);
        PeopleService peopleService = mock(PeopleService.class);
        LoanOfferService loanOfferService = mock(LoanOfferService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoanController(loanService, loanOfferService, peopleService)).build();
    }

    @Test
    public void getLoanListPage() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/loans").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/loan"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void createLoanPage() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(get("/loans/create-loan").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/loan/create-loan"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void createLoan() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        this.mockMvc.perform(post("/loans/").principal(mockPrincipal).with(user(testUserDetail)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/account"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}