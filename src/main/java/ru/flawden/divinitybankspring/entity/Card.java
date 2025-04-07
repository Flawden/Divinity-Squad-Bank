package ru.flawden.divinitybankspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

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
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            num.append(rnd.nextInt(10));
        }
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(num.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        num.append(checkDigit);

        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (i > 0 && i % 4 == 0) formatted.append(" ");
            formatted.append(num.charAt(i));
        }
        return formatted.toString();
    }

    protected final int createCVV() {
        Random rnd = new Random();
        return rnd.nextInt(888) + 111;
    }

}
