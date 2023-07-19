package ru.ustinov.autopark.util;

public class CarNotUpdatedException extends AutoparkException {
    public CarNotUpdatedException(String message) {
        super(message);
    }
}