package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.exception.NonExistentLoanException;

@Component
public class LoanUtil {

    public double calculateSumPerMonth(double totalSum, int term) {
        return totalSum / term;
    }

    public LoanEntity doLoan(LoanDTO loanDTO, LoanOffer loanOffer) {
        double sum = loanDTO.getSumm();
        int term = loanDTO.getLoanTerm();
        double interestRate = loanOffer.getInterestRate();
        double monthlyPayment = calculateSumPerMonth(sum, term);
        return new LoanEntity(sum, interestRate, monthlyPayment, term, loanDTO.getProduct());
    }
}
