package org.meditrack.appointment.feign;

import lombok.NonNull;
import org.meditrack.appointment.config.FeignConfig;
import org.meditrack.appointment.dto.AppointmentDto;
import org.meditrack.appointment.entity.Availability;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="availability-service", configuration= {FeignConfig.class})
public interface AvailabilityClient {

    @PostMapping("/api/doctor-availability/book-slots")
    ResponseEntity<@NonNull Availability> bookSlotsIfAvailable(@RequestBody AppointmentDto appointmentDto);

    @PostMapping("/api/doctor-availability/release-slots")
    void releaseSlots(@RequestBody AppointmentDto appointmentDto);
}
