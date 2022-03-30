package ru.flawden.divinitybankspring.entity.util;

import java.util.Calendar;
import java.util.Date;

public class LoanUtil {

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

    public double calculateCreditSum(double sum, int term, int interestRate) {
        double dInterestRate = interestRate;
        sum = sum * (1 + (dInterestRate / 100));

        while (term > 12) {
            term -= 12;
            sum = sum * (1 + (dInterestRate / 100));
        }

        return sum;
    }


}