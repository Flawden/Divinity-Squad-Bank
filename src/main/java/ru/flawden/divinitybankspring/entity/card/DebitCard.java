package ru.flawden.divinitybankspring.entity.card;

import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.exception.card.NegativeBalanceException;

import javax.persistence.*;
@Entity
public class DebitCard extends Card {

    public DebitCard() {
        super();
    }

    public DebitCard(String name, Person owner) {
        super(name, owner);
    }

    @Override
    public Double changeBalance(Double addedAmount) {
        if ((balance + addedAmount) < 0) {
            throw new NegativeBalanceException("Insufficient funds");
        }
        return balance += addedAmount;
    }

}
