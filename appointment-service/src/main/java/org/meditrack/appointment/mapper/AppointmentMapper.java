package org.meditrack.appointment.mapper;

import org.meditrack.appointment.dto.BookedAppointmentDto;
import org.meditrack.appointment.entity.Appointment;

public class AppointmentMapper {

    private AppointmentMapper() {}

    public static BookedAppointmentDto toDto(Appointment appointment) {

        if (appointment == null) {
            return null;
        }

        return BookedAppointmentDto.builder()
                .id(appointment.getId())
                .doctorName(appointment.getDoctorName())
                .patientName(appointment.getPatientName())
                .date(appointment.getDate())
                .startTime(appointment.getStartTime())
                .status(appointment.getStatus())
                .createdAt(appointment.getCreatedAt())
                .build();
    }
}
