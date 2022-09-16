package ru.flawden.divinitybankspring.util;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.card.CreditCard;

import java.lang.reflect.Field;

class LoanUtilTest {

    private LoanUtil loanUtil;
    private final Double summTest = 120000D;
    private final int loanTermTest = 15;

    @BeforeEach
    void setup() {
        loanUtil = new LoanUtil();
    }

    @Test
    void calculateSumPerMonth() {
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(10,2), 5);
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(20,2), 10);
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(12,2), 6);
    }

    @Test
    void calculateSumPerMonthIndivisible() {
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(9,2), 4.5);
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(3,2), 1.5);
        Assertions.assertEquals(loanUtil.calculateSumPerMonth(4,3), 1.3333333333333333);
    }

    @Test
    void doLoan() {
        LoanDTO loanDTO = new LoanDTO("TestProduct", summTest, loanTermTest);
        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setInterestRate(5D);
        CreditCard card = new CreditCard();
        Loan loan = loanUtil.doLoan(loanDTO, loanOffer, card);
        Assertions.assertEquals(loan.getInterestRate(), 5);
        Assertions.assertEquals(loan.getCreditTerm(), loanTermTest);
        Assertions.assertEquals(loan.getMonthlyPayment(), loanUtil.calculateSumPerMonth(summTest, loanTermTest));
    }
}