package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.LoanEntity;

@Component
public class LoanUtil {
//Сделать как с DebitCardUtil
    public int getInterestRate(String product) {
        int interestRate = 0;

        if (product.equals("carowner")) {
            return 9;
        } else if (product.equals("newhouse")) {
            return 6;
        } else if (product.equals("payday")) {
            return 25;
        }

        return interestRate;
    }
    public double calculateSumPerMonth(double totalSum, int term) {
        return totalSum / term;
    }

    public LoanEntity doLoan(LoanDTO loanDTO) {

        double sum = loanDTO.getSumm();
        int term = loanDTO.getLoanTerm();
        int interestRate = getInterestRate(loanDTO.getProduct());
        double monthlyPayment = calculateSumPerMonth(sum, term);

        return new LoanEntity(sum, interestRate, monthlyPayment, term);
    }


}
