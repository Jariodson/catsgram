package ru.yandex.practicum.catsgram.exceiption;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message) {
        super(message);
    }
}
