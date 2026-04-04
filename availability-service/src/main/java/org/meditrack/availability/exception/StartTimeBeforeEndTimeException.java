package org.meditrack.availability.exception;

public class StartTimeBeforeEndTimeException extends RuntimeException {

    public StartTimeBeforeEndTimeException(String message) {
        super(message);
    }
}
