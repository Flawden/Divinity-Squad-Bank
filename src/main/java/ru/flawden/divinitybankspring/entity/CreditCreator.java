package ru.flawden.divinitybankspring.entity;

import java.util.Calendar;
import java.util.Date;

public class CreditCreator {

    public long returnTermInMonths(Date date) {

        Date dateNow = new Date();

        long time = dateNow.getTime() - date.getTime();

        return time / 2592000000L;
    }

    public int returnInterestRate(String product) {

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
