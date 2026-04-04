package org.meditrack.appointment.exception;

public class DoctorNotAvailableAtThatTime extends BusinessException {
    public DoctorNotAvailableAtThatTime(String message) {
        super(message);
    }
}
