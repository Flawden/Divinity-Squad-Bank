package ru.flawden.divinitybankspring.util;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.card.CreditCard;

/**
 * Utility class for performing loan calculations and operations.
 *
 * @author Flawden
 * @version 1.0
 */
@Component
public class LoanUtil {

    /**
     * Calculates the monthly payment for a loan based on the total sum, term, and interest rate.
     *
     * @param totalSum the total amount of the loan.
     * @param term     the term of the loan in months.
     * @param interestRate the annual interest rate of the loan.
     * @return the calculated monthly payment.
     */
    public double calculateSumPerMonth(double totalSum, int term, double interestRate) {
        double monthlyRate = interestRate / 100 / 12;
        return totalSum * (monthlyRate * Math.pow(1 + monthlyRate, term)) / (Math.pow(1 + monthlyRate, term) - 1);
    }

    /**
     * Calculates the total sum to be paid over the entire loan term including interest.
     *
     * @param monthlyPayment the monthly payment amount.
     * @param term          the term of the loan in months.
     * @return the total sum including interest.
     */
    public double calculateSumTotalWithInterestRate(double monthlyPayment, int term) {
        return monthlyPayment * term;
    }

    /**
     * Creates a Loan object based on the provided loan data transfer object, loan offer, and credit card.
     *
     * @param loanDTO   the data transfer object containing loan information.
     * @param loanOffer the loan offer associated with the loan.
     * @param card      the credit card associated with the loan.
     * @return a Loan object populated with calculated values.
     */
    public Loan doLoan(LoanDTO loanDTO, LoanOffer loanOffer, CreditCard card) {
        double sum = loanDTO.getSum();
        int term = loanDTO.getCreditTerm();
        double interestRate = loanOffer.getInterestRate();
        double monthlyPayment = calculateSumPerMonth(sum, term, loanOffer.getInterestRate());
        sum = calculateSumTotalWithInterestRate(monthlyPayment, term);
        return new Loan(sum, interestRate, monthlyPayment, term, loanDTO.getCreditName(), card);
    }
}
