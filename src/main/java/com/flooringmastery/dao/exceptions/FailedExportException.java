package com.flooringmastery.dao.exceptions;

public class FailedExportException extends Exception {
    public FailedExportException(String message) {
        super(message);
    }

    public FailedExportException(String message, Throwable cause) {
        super(message, cause);
    }    
}
