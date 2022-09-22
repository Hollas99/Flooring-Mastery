package com.flooringmastery.dao.exceptions;

public class FailedSaveException extends Exception {
    public FailedSaveException(String message) {
        super(message);
    }

    public FailedSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
