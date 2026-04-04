package org.meditrack.appointment.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.meditrack.appointment.dto.AppointmentDto;
import org.meditrack.appointment.dto.BookedAppointmentDto;
import org.meditrack.appointment.entity.Appointment;
import org.meditrack.appointment.entity.Availability;
import org.meditrack.appointment.entity.Status;
import org.meditrack.appointment.exception.DcotorNotBoookedException;
import org.meditrack.appointment.mapper.AppointmentMapper;
import org.meditrack.appointment.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.meditrack.appointment.dto.AppointmentCreatedEventDto;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailabilityGatewayService availabilityGatewayService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EventPublisherService eventPublisherService;

    @Transactional
    public BookedAppointmentDto bookAppointment(AppointmentDto appointmentDto, Authentication authentication) {

        Availability availability = availabilityGatewayService.bookSlot(appointmentDto);

        logger.info("Checking if appointment is available {}", availability);

            if (availability != Availability.BOOKED) {
                throw new DcotorNotBoookedException("Failed to book the appointment. Please try again.");
            }
            try {
                Appointment appointment = new Appointment();
                appointment.setDoctorName(appointmentDto.getDoctorName());
                appointment.setPatientName(authentication.getName());
                appointment.setDate(appointmentDto.getDate());
                appointment.setStartTime(appointmentDto.getStartTime());
                appointment.setStatus(Status.BOOKED);
                appointmentRepository.save(appointment);

                AppointmentCreatedEventDto event= new AppointmentCreatedEventDto();
                event.setEventType("APPOINTMENT_CREATED");
                event.setAppointmentId(String.valueOf(appointment.getId()));
                event.setPatientName(appointment.getPatientName());
                event.setDoctorName((appointment.getDoctorName()));
                event.setAppointmentTime(appointment.getStartTime().toString());

                //eventProducer.publishAppointmentCreated(event);

                eventPublisherService.publishEvent(event);

                return AppointmentMapper.toDto(appointment);
            }catch (Exception e){
                availabilityGatewayService.releaseSlots(appointmentDto);
                throw new DcotorNotBoookedException("Failed to book the appointment. Please try again.");
            }
    }

}
