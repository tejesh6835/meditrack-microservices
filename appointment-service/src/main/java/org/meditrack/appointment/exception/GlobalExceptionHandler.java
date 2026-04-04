package org.meditrack.appointment.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.meditrack.appointment.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DoctorNotExistsException.class)
    public ResponseEntity<@NonNull ApiError> handleStartTimeEndTimeException(DoctorNotExistsException ex,
                                                                             HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(DcotorNotBoookedException.class)
    public ResponseEntity<@NonNull ApiError> handleDcotorNotBoookedException(DcotorNotBoookedException ex,
                                                                                HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(DoctorNotAvailableAtThatTime.class)
    public ResponseEntity<@NonNull ApiError> handleDDoctorNotAvailableAtThatTime(DoctorNotAvailableAtThatTime ex,
                                                                             HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(AvailabilityServiceUnavailableException.class)
    public ResponseEntity<@NonNull ApiError> handleAvailabilityServiceUnavailableException(AvailabilityServiceUnavailableException ex,
                                                                             HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ApiError(Instant.now(), 503, ex.getMessage(), req.getRequestURI()));
    }
}
