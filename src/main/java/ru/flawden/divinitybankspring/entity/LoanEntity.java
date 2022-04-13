package ru.flawden.divinitybankspring.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Loan")
public class LoanEntity {

    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "issueDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date issueDate = new Date();

    @Column(name = "sum")
    private Double sum;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "credit_term")
    private Integer creditTerm;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LoanEntity() {

    }

    public LoanEntity(double sum, double interestRate, double monthlyPayment, int creditTerm) {
        this.issueDate = new Date();
        this.sum = sum;
        this.interestRate = interestRate;
        this.monthlyPayment = monthlyPayment;
        this.creditTerm = creditTerm;
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

    @Override
    public String toString() {
        return "Credit:\n" +
                "Credit for the amount of " + sum + ", for a period of " + creditTerm + " months.\n" +
                "Monthly payment: " + monthlyPayment + " rub\n" +
                "Taken: " + simpleDateFormat.format(issueDate) + "\n";
    }

}
