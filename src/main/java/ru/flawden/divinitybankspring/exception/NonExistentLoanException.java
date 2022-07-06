package ru.flawden.divinitybankspring.exception;

public class NonExistentLoanException extends RuntimeException {

    public NonExistentLoanException() {super();}
    public NonExistentLoanException(String message) {
        super(message);
    }

}
