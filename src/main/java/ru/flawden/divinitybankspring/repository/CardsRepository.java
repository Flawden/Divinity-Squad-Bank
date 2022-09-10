package ru.flawden.divinitybankspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flawden.divinitybankspring.entity.Card;
import ru.flawden.divinitybankspring.entity.Person;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Card, Integer> {

    List<Card> findAllByOwner(Person owner);

}
