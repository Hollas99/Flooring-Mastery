package com.flooringmastery.dao.exceptions;

public class FailedLoadException extends Exception {
    public FailedLoadException(String message) {
        super(message);
    }

    public FailedLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
