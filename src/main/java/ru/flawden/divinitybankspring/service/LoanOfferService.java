package ru.flawden.divinitybankspring.service;

import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.entity.LoanOffer;
import ru.flawden.divinitybankspring.repository.LoanOfferRepository;

import java.util.List;

/**
 * Service class for managing loan offers.
 * Provides methods to retrieve all loan offers available in the system.
 *
 * @author Flawden
 * @version 1.0
 */
@Service
public class LoanOfferService {

    private final LoanOfferRepository loanOfferRepository;

    public LoanOfferService(LoanOfferRepository loanOfferRepository) {
        this.loanOfferRepository = loanOfferRepository;
    }

    /**
     * Finds all loan offers available in the repository.
     *
     * @return A list of loan offers.
     */
    public List<LoanOffer> findAll() {
        return loanOfferRepository.findAll();
    }
}
