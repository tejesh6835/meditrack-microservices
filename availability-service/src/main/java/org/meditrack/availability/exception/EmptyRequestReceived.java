package org.meditrack.availability.exception;

public class EmptyRequestReceived extends RuntimeException {

    public EmptyRequestReceived(String message) {
        super(message);
    }
}
