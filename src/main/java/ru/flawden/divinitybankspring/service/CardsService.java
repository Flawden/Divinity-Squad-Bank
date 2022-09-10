package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.card.DebitCard;
import ru.flawden.divinitybankspring.exception.UserNotFoundException;
import ru.flawden.divinitybankspring.repository.CardsRepository;
import ru.flawden.divinitybankspring.repository.PeopleRepository;

import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;
    private final PeopleRepository peopleRepository;

    public CardsService(CardsRepository cardsRepository, PeopleRepository peopleRepository) {
        this.cardsRepository = cardsRepository;
        this.peopleRepository = peopleRepository;
    }

    public void save(String name) {
        cardsRepository.save(new DebitCard("Default debit card",
                peopleRepository.findByEmail(name).orElseThrow(() ->
                        new UserNotFoundException("User not found"))));
    }

    public List<Card> findAllByPerson(Person person) {
        return cardsRepository.findAllByOwner(person);
    }
}
