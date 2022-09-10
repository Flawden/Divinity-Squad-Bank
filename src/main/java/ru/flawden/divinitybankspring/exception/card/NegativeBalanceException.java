package ru.flawden.divinitybankspring.exception.card;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException() {super();}
    public NegativeBalanceException(String message) {
        super(message);
    }

}
