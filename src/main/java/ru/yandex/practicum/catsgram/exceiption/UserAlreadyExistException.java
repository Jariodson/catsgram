package ru.yandex.practicum.catsgram.exceiption;

public class UserAlreadyExistException  extends RuntimeException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
