package ru.flawden.divinitybankspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.service.CardsService;
import ru.flawden.divinitybankspring.service.PeopleService;
import ru.flawden.divinitybankspring.util.AuthUtil;

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
    private final AuthUtil authUtil;

    public CardController(CardsService cardsService, AuthUtil authUtil) {
        this.cardsService = cardsService;
        this.authUtil = authUtil;
    }

    /**
     * Displays the card list page, showing all cards associated with the logged-in user.
     *
     * @param model Holds the list of cards to be displayed.
     * @return The card list view.
     */
    @GetMapping
    public String getCardListPage(Model model) {
        Person person = authUtil.getCurrentUser();
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
     * @return Redirects to the account page after creating a debit card.
     */
    @GetMapping("/create-debit-card")
    public String createDebitCard() {
        cardsService.save(authUtil.getCurrentUser().getEmail());
        return "redirect:/account";
    }

}
