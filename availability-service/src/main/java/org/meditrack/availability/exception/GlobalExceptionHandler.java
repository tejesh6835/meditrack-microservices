package org.meditrack.availability.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.meditrack.availability.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StartTimeBeforeEndTimeException.class)
    public ResponseEntity<@NonNull ApiError> handleStartTimeEndTimeException(StartTimeBeforeEndTimeException ex,
                                                                   HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(EmptyRequestReceived.class)
    public ResponseEntity<@NonNull ApiError> handleEmptyRequestReceived(EmptyRequestReceived ex,
                                                                             HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<@NonNull ApiError> handleAlreadyRegisteredException(AlreadyRegisteredException ex,
                                                                         HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(DoctorNotExistsException.class)
    public ResponseEntity<@NonNull ApiError> handleDoctorNotExistsException(DoctorNotExistsException ex,
                                                                              HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(DoctorNotAvailableAtThatTime.class)
    public ResponseEntity<@NonNull ApiError> handleDoctorNotAvailableAtThatTime(DoctorNotAvailableAtThatTime ex,
                                                                            HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(Instant.now(), 409, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(DoctorAlreadyBookedException.class)
    public ResponseEntity<@NonNull ApiError> handleDoctorAlreadyBookedException(DoctorAlreadyBookedException ex,
                                                                            HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(Instant.now(), 400, ex.getMessage(), req.getRequestURI()));
    }
}
