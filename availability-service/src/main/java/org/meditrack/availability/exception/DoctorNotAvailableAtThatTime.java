package org.meditrack.availability.exception;

public class DoctorNotAvailableAtThatTime extends  RuntimeException {
    public DoctorNotAvailableAtThatTime(String message) {
        super(message);
    }
}
