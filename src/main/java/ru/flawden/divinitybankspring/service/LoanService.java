package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.dao.LoanDAO;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.UserEntity;
import ru.flawden.divinitybankspring.util.LoanUtil;

@Service
public class LoanService {

    private final UserDAO userDAO;
    private final LoanUtil loanUtil;
    private final LoanDAO loanDAO;

    public LoanService(UserDAO userDAO, LoanUtil loanUtil, LoanDAO loanDAO) {
        this.userDAO = userDAO;
        this.loanUtil = loanUtil;
        this.loanDAO = loanDAO;
    }

    public void addLoan(LoanEntity loan, UserEntity user) {
        userDAO.addLoan(loan, user);
    }

    public LoanEntity doLoan(LoanDTO loanDTO) {
        LoanOffer loanOffer = loanDAO.getLoanOfferByName(loanDTO.getProduct());
        return loanUtil.doLoan(loanDTO, loanOffer);
    }
}
