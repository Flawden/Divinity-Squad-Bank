package ru.flawden.divinitybankspring.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DebitCard implements Serializable {

    private final Calendar createdDate;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Double balance;
    private String cardNumber;
    private Date expirationDate;
    private int cvv;

    public DebitCard(String cardNumber, int cvv) {
        this.createdDate = Calendar.getInstance();
        this.balance = 0.0;
        this.cardNumber = cardNumber;
        this.createdDate.add(Calendar.YEAR, 1);
        this.expirationDate = createdDate.getTime();
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public Calendar getCreatedDate() {return createdDate;}

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Balance: " + balance + "\n" +
                "Card number: " + cardNumber + "\n" +
                "Expiration date: " + simpleDateFormat.format(expirationDate) + "\n" +
                "CVV code: " + cvv + "\n";
    }

}