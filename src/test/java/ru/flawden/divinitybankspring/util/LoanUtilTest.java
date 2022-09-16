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
    private final double testInterestRate = 12;

    @BeforeEach
    void setup() {
        loanUtil = new LoanUtil();
    }

    @Test
    void calculateSumTotalWithInterestRate() {
        Assertions.assertEquals(158545.44, loanUtil.calculateSumTotalWithInterestRate(13212.12, 12));
    }

    @Test
    void calculateSumPerMonth() {
        Assertions.assertEquals(664.2861962570233, loanUtil.calculateSumPerMonth(20000,36, testInterestRate));
    }

    @Test
    void doLoan() {
        LoanDTO loanDTO = new LoanDTO("TestProduct", summTest, loanTermTest);
        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setInterestRate(5D);
        CreditCard card = new CreditCard();
        Loan loan = loanUtil.doLoan(loanDTO, loanOffer, card);
        Assertions.assertEquals(5, loan.getInterestRate());
        Assertions.assertEquals(loanTermTest, loan.getCreditTerm());
    }
}