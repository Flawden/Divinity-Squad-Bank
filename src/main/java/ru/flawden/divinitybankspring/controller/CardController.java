package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.CardsService;
import ru.flawden.divinitybankspring.service.PeopleService;

import java.security.Principal;
import java.util.List;

/**
 * Controller for handling card-related operations, such as listing cards and creating new debit cards.
 *
 * @author Flawden
 * @version 1.0
 */
@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardsService cardsService;
    private final PeopleService peopleService;

    public CardController(CardsService cardsService, PeopleService peopleService) {
        this.cardsService = cardsService;
        this.peopleService = peopleService;
    }

    /**
     * Displays the card list page, showing all cards associated with the logged-in user.
     *
     * @param model Holds the list of cards to be displayed.
     * @param principal The logged-in user's principal object.
     * @return The card list view.
     */
    @GetMapping
    public String getCardListPage(Model model, Principal principal) {
        Person person = peopleService.findByEmail(principal.getName());
        List<Card> cards = cardsService.findAllByPerson(person);
        model.addAttribute("cards", cards);
        return "profile/cards";
    }

    /**
     * Displays the card selection page where users can choose a card type.
     *
     * @return The card selection view.
     */
    @GetMapping("/select-card")
    public String selectCard() {
        return "profile/card/select-card";
    }

    /**
     * Creates a new debit card for the logged-in user and redirects to the account page.
     *
     * @param principal The logged-in user's principal object.
     * @return Redirects to the account page after creating a debit card.
     */
    @GetMapping("/create-debit-card")
    public String createDebitCard(Principal principal) {
        cardsService.save(principal.getName());
        return "redirect:/account";
    }

}
