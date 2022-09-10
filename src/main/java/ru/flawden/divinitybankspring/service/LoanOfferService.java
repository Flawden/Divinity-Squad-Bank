package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.repository.LoanOfferRepository;

import java.util.List;

@Service
public class LoanOfferService {

    private final LoanOfferRepository loanOfferRepository;

    public LoanOfferService(LoanOfferRepository loanOfferRepository) {
        this.loanOfferRepository = loanOfferRepository;
    }

    public List<LoanOffer> findAll() {
        return loanOfferRepository.findAll();
    }
}
