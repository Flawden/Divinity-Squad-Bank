package ru.flawden.divinitybankspring.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Random;

@Entity
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 0, message = "Balance cannot be negative")
    protected Double balance;
    protected final String number;
    protected String name;
    protected final Integer cvv;

    public Double getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCvv() {
        return cvv;
    }

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
//                part = rnd.nextInt(1111,9999);
                num.append(part).append(" ");
            }
            break;
        }
        return num.toString();
    }

    protected final int createCVV() {
        Random rnd = new Random();
//        return rnd.nextInt(111,999);
        return rnd.nextInt(888) + 111;
    }

}
