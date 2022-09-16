package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.CardsService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardsService cardsService;
    private final PeopleService peopleService;

    public CardController(CardsService cardsService, PeopleService peopleService) {
        this.cardsService = cardsService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getCardListPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        List<Card> cards = cardsService.findAllByPerson(person);
        model.addAttribute("cards", cards);
        return "profile/cards";
    }

    @GetMapping("/select-card")
    public String selectCard() {
        return "profile/card/select-card";
    }

    @GetMapping("/create-debit-card")
    public String createDebitCard(Principal principal) {
        cardsService.save(principal.getName());
        return "redirect:/account";
    }

}
