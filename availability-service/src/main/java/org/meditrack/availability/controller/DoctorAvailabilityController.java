package org.meditrack.availability.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.meditrack.availability.dto.AppointmentDto;
import org.meditrack.availability.dto.DoctorAvailabilityDto;
import org.meditrack.availability.dto.TimeSlotDto;
import org.meditrack.availability.entity.Availability;
import org.meditrack.availability.entity.TimeSlot;
import org.meditrack.availability.service.DoctorAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    @PostMapping("/api/doctor-availability")
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    ResponseEntity<@NonNull List<TimeSlotDto>> fillDoctorAvailability(@RequestBody DoctorAvailabilityDto doctorAvailability,
                                                                      Authentication authentication) {
        return ResponseEntity.ok(doctorAvailabilityService.fillDoctorAvailability(doctorAvailability, authentication));
    }

    @GetMapping("/api/doctor-availability/slots/me")
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    ResponseEntity<@NonNull List<TimeSlotDto>> getMySlots(@RequestParam LocalDate date, Authentication authentication) {
        return ResponseEntity.ok(doctorAvailabilityService.getMySlots(date, authentication));
    }

    @GetMapping("/api/doctor-availability/slots")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    ResponseEntity<@NonNull List<TimeSlotDto>> getAvailableSlots(@RequestParam String doctorName, @RequestParam LocalDate date) {
        return ResponseEntity.ok(doctorAvailabilityService.getAvailableSlots(doctorName, date));
    }

//    @PostMapping("/api/doctor-availability/check-slots")
//    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
//    ResponseEntity<@NonNull Availability> checkSlotsExists(@RequestBody AppointmentDto appointmentDto) {
//    return ResponseEntity.ok(doctorAvailabilityService.checkSlotsExists(appointmentDto));
//    }
//
//    @PostMapping("/api/doctor-availability/set-slot-booked")
//    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
//    ResponseEntity<@NonNull Availability> setSlotBooked(@RequestBody AppointmentDto appointmentDto) {
//        return ResponseEntity.ok(doctorAvailabilityService.setSlotBooked(appointmentDto));
//    }

    @PostMapping("/api/doctor-availability/book-slots")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    ResponseEntity<@NonNull Availability> bookSlotsIfAvailable(@RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(doctorAvailabilityService.setSlotBooked(appointmentDto));
    }

    @PostMapping("/api/doctor-availability/release-slots")
    void releaseSlots(@RequestBody AppointmentDto appointmentDto){
        doctorAvailabilityService.releaseSlots(appointmentDto);
    }
}
