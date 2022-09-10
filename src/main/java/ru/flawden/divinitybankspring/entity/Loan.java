package ru.flawden.divinitybankspring.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ru.flawden.divinitybankspring.entity.card.CreditCard;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Loan {

    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issuedate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date issueDate = new Date();

    @Column(name = "sum")
    private Double sum;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "credit_term")
    private Integer creditTerm;

    @Column(name = "credit_name")
    private String creditName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Person owner;

    @OneToOne
    private CreditCard card;

    public Loan() {}

    public Loan(double sum, double interestRate, double monthlyPayment, int creditTerm, String creditName, CreditCard card) {
        this.creditName = creditName;
        this.issueDate = new Date();
        this.sum = sum;
        this.interestRate = interestRate;
        this.monthlyPayment = monthlyPayment;
        this.creditTerm = creditTerm;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Integer getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Integer creditTerm) {
        this.creditTerm = creditTerm;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Credit:\n" +
                "Credit for the amount of " + sum + ", for a period of " + creditTerm + " months.\n" +
                "Monthly payment: " + monthlyPayment + " rub\n" +
                "Taken: " + simpleDateFormat.format(issueDate) + "\n";
    }
}
