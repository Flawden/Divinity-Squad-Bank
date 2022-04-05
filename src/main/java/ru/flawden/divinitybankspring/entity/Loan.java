package ru.flawden.divinitybankspring.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan implements Serializable {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Date issueDate = new Date();
    private Double sum;
    private Double interestRate;
    private Double monthlyPayment;
    private Integer creditTerm;

    public Loan() {

    }

    public Loan(double sum, double interestRate, double monthlyPayment, int creditTerm) {
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
