package org.meditrack.appointment.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.meditrack.appointment.dto.AppointmentDto;
import org.meditrack.appointment.dto.BookedAppointmentDto;
import org.meditrack.appointment.entity.Appointment;
import org.meditrack.appointment.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @PostMapping("/api/appointments")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<@NonNull BookedAppointmentDto> bookAppointments(@RequestBody AppointmentDto appointmentDto, Authentication authentication) {
        return ResponseEntity.ok(appointmentService.bookAppointment(appointmentDto, authentication));
    }
}
