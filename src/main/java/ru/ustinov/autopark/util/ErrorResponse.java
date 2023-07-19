package ru.ustinov.autopark.util;

public class ErrorResponse {
    private String message;
    public ErrorResponse(String message, long ignoredTimestamp) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
