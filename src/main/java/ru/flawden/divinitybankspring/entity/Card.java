package ru.flawden.divinitybankspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Random;

@Entity
@Getter
@Setter
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 0, message = "Balance cannot be negative")
    protected Double balance;
    protected final String number;
    protected String name;
    protected final Integer cvv;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Card() {
        this.number = createCardNumber();
        this.cvv = createCVV();
        this.balance = 0.0d;
    }

    public Card(String name, Person owner) {
        this.number = createCardNumber();
        this.cvv = createCVV();
        this.name = name;
        this.balance = 0.0d;
        this.owner = owner;
    }

    public abstract Double changeBalance(Double addedAmount);

    @Override
    public String toString() {
        return "Card: " + name + "\n" +
                "Balance: " + balance + "\n" +
                "Card number:" + number;
    }

    protected final String createCardNumber() {
        Random rnd = new Random();
        int part = 0;
        StringBuilder num = new StringBuilder();

        while (true) {
            for (int i = 0; i < 4; i++) {
                part = rnd.nextInt(8888) + 1111;
                num.append(part).append(" ");
            }
            break;
        }
        return num.toString();
    }

    protected final int createCVV() {
        Random rnd = new Random();
        return rnd.nextInt(888) + 111;
    }

}
