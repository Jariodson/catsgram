package ru.yandex.practicum.catsgram.exceiption;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
