package ru.flawden.divinitybankspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flawden.divinitybankspring.entity.LoanOffer;

import java.util.Optional;

@Repository
public interface LoanOfferRepository extends JpaRepository<LoanOffer, Integer> {

    Optional<LoanOffer> findLoanOfferByCreditName(String productName);

}
