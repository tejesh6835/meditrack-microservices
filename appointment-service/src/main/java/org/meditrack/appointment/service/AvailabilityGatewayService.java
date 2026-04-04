package org.meditrack.appointment.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.meditrack.appointment.dto.AppointmentDto;
import org.meditrack.appointment.entity.Availability;
import org.meditrack.appointment.exception.AvailabilityServiceUnavailableException;
import org.meditrack.appointment.exception.BusinessException;
import org.meditrack.appointment.feign.AvailabilityClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailabilityGatewayService {

    private final AvailabilityClient availabilityClient;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Retry(name="availability")
    @CircuitBreaker(name = "availability", fallbackMethod = "availabilityFallbackForBooking")
    public Availability bookSlot(AppointmentDto appointmentDto) {
        return availabilityClient.bookSlotsIfAvailable(appointmentDto).getBody();
    }

    @Retry(name="availability")
    @CircuitBreaker(name = "availability", fallbackMethod = "availabilityFallbackForSlotRelease")
    public void releaseSlots(AppointmentDto appointmentDto) {
        availabilityClient.releaseSlots(appointmentDto);
    }

    public Availability availabilityFallbackForBooking(AppointmentDto dto, Throwable ex) throws Throwable {

        if(ex instanceof BusinessException) {
            throw ex;
        }
        logger.error("Availability service down", ex);

        throw new AvailabilityServiceUnavailableException("Booking service temporarily unavailable");
    }

    public void availabilityFallbackForSlotRelease(AppointmentDto dto, Throwable ex) throws Throwable {

        logger.error("CRITICAL: Compensation failed. Slot may remain booked!", ex);

    }

}
