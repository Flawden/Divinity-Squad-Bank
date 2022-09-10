package ru.flawden.divinitybankspring.exception;

public class NonExistentGenderException extends RuntimeException {

    public NonExistentGenderException() {super();}
    public NonExistentGenderException(String message) {
        super(message);
    }

}
