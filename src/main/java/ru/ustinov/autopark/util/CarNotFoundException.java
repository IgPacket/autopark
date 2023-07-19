package ru.ustinov.autopark.util;

public class CarNotFoundException extends AutoparkException {
    public CarNotFoundException(String message) {
        super(message);
    }
}