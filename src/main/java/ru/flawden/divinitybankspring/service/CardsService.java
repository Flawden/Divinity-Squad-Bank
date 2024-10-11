package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.card.DebitCard;
import ru.flawden.divinitybankspring.exception.UserNotFoundException;
import ru.flawden.divinitybankspring.repository.CardsRepository;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.List;

/**
 * Service class for managing cards.
 * Provides methods to save new cards and retrieve cards associated with a person.
 *
 * @author Flawden
 * @version 1.0
 */
@Service
public class CardsService {

    private final CardsRepository cardsRepository;
    private final PeopleRepository peopleRepository;

    public CardsService(CardsRepository cardsRepository, PeopleRepository peopleRepository) {
        this.cardsRepository = cardsRepository;
        this.peopleRepository = peopleRepository;
    }

    /**
     * Saves a new debit card for the specified user.
     *
     * @param name The email of the user.
     * @throws UserNotFoundException if the user is not found in the repository.
     */
    public void save(String name) {
        cardsRepository.save(new DebitCard("Default debit card",
                peopleRepository.findByEmail(name).orElseThrow(() ->
                        new UserNotFoundException("User not found"))));
    }

    /**
     * Finds all cards associated with the specified person.
     *
     * @param person The person whose cards are to be retrieved.
     * @return A list of cards owned by the person.
     */
    public List<Card> findAllByPerson(Person person) {
        return cardsRepository.findAllByOwner(person);
    }
}
