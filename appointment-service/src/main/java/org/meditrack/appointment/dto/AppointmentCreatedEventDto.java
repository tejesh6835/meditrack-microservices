package org.meditrack.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreatedEventDto {

    private String eventType;

    private String appointmentId;
    private String patientName;
    private String doctorName;
    private String appointmentTime;
}
