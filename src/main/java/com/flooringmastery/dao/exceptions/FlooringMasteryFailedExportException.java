package com.flooringmastery.dao.exceptions;

public class FlooringMasteryFailedExportException extends Exception {
    public FlooringMasteryFailedExportException(String message) {
        super(message);
    }

    public FlooringMasteryFailedExportException(String message, Throwable cause) {
        super(message, cause);
    }    
}
