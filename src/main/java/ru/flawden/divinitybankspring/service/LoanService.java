package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.dto.LoanDTO;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.entity.card.CreditCard;
import ru.flawden.divinitybankspring.repository.CardsRepository;
import ru.flawden.divinitybankspring.repository.LoanOfferRepository;
import ru.flawden.divinitybankspring.repository.LoanRepository;
import ru.flawden.divinitybankspring.util.LoanUtil;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoanService {

    private final LoanUtil loanUtil;
    private final PeopleService peopleService;
    private final LoanOfferRepository loanOfferRepository;
    private final CardsRepository cardsRepository;
    private final LoanRepository loanRepository;

    public LoanService(LoanUtil loanUtil, PeopleService peopleService, LoanOfferRepository loanOfferRepository, CardsRepository cardsRepository, LoanRepository loanRepository) {
        this.loanUtil = loanUtil;
        this.peopleService = peopleService;
        this.loanOfferRepository = loanOfferRepository;
        this.cardsRepository = cardsRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public void save(LoanDTO loanDTO, String ownerEmail) {
        Person person = peopleService.findByEmail(ownerEmail);
        LoanOffer loanOffer = loanOfferRepository.findLoanOfferByCreditName(loanDTO.getProduct()).orElseThrow(() -> new RuntimeException("Loan offer doesn't exist"));
        CreditCard card = cardsRepository.save(new CreditCard(loanDTO.getSumm(), person, "Default credit card"));
        Loan loan = loanUtil.doLoan(loanDTO, loanOffer, card);
        loan.setOwner(person);
        Loan loanId = loanRepository.save(loan);
        card.setLoan(loanId);
        cardsRepository.save(card);
    }

    public List<Loan> findAllByPerson(Person person) {
        return loanRepository.findAllByOwner(person);
    }
}
