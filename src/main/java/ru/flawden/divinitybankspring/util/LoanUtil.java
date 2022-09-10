package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.card.CreditCard;

@Component
public class LoanUtil {

    public double calculateSumPerMonth(double totalSum, int term) {
        return totalSum / term;
    }

    public Loan doLoan(LoanDTO loanDTO, LoanOffer loanOffer, CreditCard card) {
        double sum = loanDTO.getSumm();
        int term = loanDTO.getLoanTerm();
        double interestRate = loanOffer.getInterestRate();
        double monthlyPayment = calculateSumPerMonth(sum, term);
        return new Loan(sum, interestRate, monthlyPayment, term, loanDTO.getProduct(), card);
    }
}
