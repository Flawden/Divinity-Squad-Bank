package ru.flawden.divinitybankspring.entity.card;

import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.exception.card.NegativeBalanceException;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class CreditCard extends Card {

    private final Double takenLoan;

    @OneToOne
    private Loan loan;

    public CreditCard() {
        this.takenLoan = 0.0d;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public CreditCard(Double takenLoan, Person owner, String name) {
        super(name, owner);
        this.takenLoan = takenLoan;
        this.balance += takenLoan;
    }

    @Override
    public Double changeBalance(Double addedAmount) {
        if ((balance + addedAmount) < (-1 * takenLoan)) {
            throw new NegativeBalanceException("Insufficient funds");
        }
        return balance += addedAmount;
    }
}
