package ru.ustinov.autopark.util;

public class CarNotCreatedException extends AutoparkException {
    public CarNotCreatedException(String message) {
        super(message);
    }
}