package com.flooringmastery.dao.exceptions;

public class FlooringMasteryFailedSaveException extends Exception {
    public FlooringMasteryFailedSaveException(String message) {
        super(message);
    }

    public FlooringMasteryFailedSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
