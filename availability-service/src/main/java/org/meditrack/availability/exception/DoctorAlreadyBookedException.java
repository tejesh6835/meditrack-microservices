package org.meditrack.availability.exception;

public class DoctorAlreadyBookedException extends RuntimeException {

    public DoctorAlreadyBookedException(String message) {
        super(message);
    }

}
