package ru.flawden.divinitybankspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.Person;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findAllByOwner(Person owner);

}
