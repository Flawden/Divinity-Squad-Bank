package ru.flawden.divinitybankspring.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "debitcard")
public class DebitCardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Calendar calendar = Calendar.getInstance();

    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date createdDate = calendar.getTime();

    @Column(name = "balance")
    private Double balance;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date expirationDate;

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardowner_id")
    private UserEntity cardOwner;

    public UserEntity getUser() {
        return cardOwner;
    }

    public void setUser(UserEntity user) {
        this.cardOwner = user;
    }

    public DebitCardEntity() {}

    public DebitCardEntity(String cardNumber, int cvv) {
        this.createdDate = calendar.getTime();
        this.balance = 0.0;
        this.cardNumber = cardNumber;
        this.calendar.add(Calendar.YEAR, 1);
        this.expirationDate = calendar.getTime();
        this.cvv = cvv;
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
