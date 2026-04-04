package org.meditrack.apigateway.controller;

import org.meditrack.apigateway.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class GatewayFallbackController {
        // Fallback for Appointment Service
        @RequestMapping("/fallback/appointment-service")
        public ResponseEntity<ApiError> appointmentServiceFallback() {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ApiError(Instant.now(), 503, "Appointment Service is currently unavailable. Please try again later.", "appointment-service"));
        }

        // Fallback for Availability Service
        @RequestMapping("/fallback/availability-service")
        public ResponseEntity<ApiError> availabilityServiceFallback() {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ApiError(Instant.now(), 503, "Availability Service is currently unavailable. Please try again later.", "availability-service"));
        }
        // Fallback for User Service
        @RequestMapping("/fallback/user-service")
        public ResponseEntity<ApiError> userServiceFallback() {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ApiError(Instant.now(), 503, "User Service is currently unavailable. Please try again later.", "user-service"));
        }
}
