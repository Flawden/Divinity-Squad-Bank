package ru.flawden.divinitybankspring.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DebitCard implements Serializable {

    private Calendar calendar = Calendar.getInstance();
    private Date createdDate = calendar.getTime();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Double balance;
    private String cardNumber;
    private Date expirationDate;
    private int cvv;

    public DebitCard() {

    }

    public DebitCard(String cardNumber, int cvv) {
        this.createdDate = calendar.getTime();
        this.balance = 0.0;
        this.cardNumber = cardNumber;
        this.calendar.add(Calendar.YEAR, 1);
        this.expirationDate = calendar.getTime();
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public long getCreatedDate() {return createdDate.getTime();}

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

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