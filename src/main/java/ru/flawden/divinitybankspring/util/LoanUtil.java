package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.card.CreditCard;

@Component
public class LoanUtil {

    public double calculateSumPerMonth(double totalSum, int term, double interestRate) {
        double termPerMonth = interestRate / (100d * interestRate);
        return (double) totalSum * (termPerMonth / (1 - (Math.pow((termPerMonth + 1), -term))));
    }

    public double calculateSumTotalWithInterestRate(double monthlyPayment, int term) {
        return monthlyPayment * term;
    }

    public Loan doLoan(LoanDTO loanDTO, LoanOffer loanOffer, CreditCard card) {
        double sum = loanDTO.getSum();
        int term = loanDTO.getCreditTerm();
        double interestRate = loanOffer.getInterestRate();
        double monthlyPayment = calculateSumPerMonth(sum, term, loanOffer.getInterestRate());
        sum = calculateSumTotalWithInterestRate(monthlyPayment, term);
        return new Loan(sum, interestRate, monthlyPayment, term, loanDTO.getCreditName(), card);
    }
}
