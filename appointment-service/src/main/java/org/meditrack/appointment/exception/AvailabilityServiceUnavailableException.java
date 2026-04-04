package org.meditrack.appointment.exception;

public class AvailabilityServiceUnavailableException extends  RuntimeException {
    public AvailabilityServiceUnavailableException(String message) {
        super(message);
    }
}
