package org.meditrack.appointment.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppointmentDto {
    private String doctorName;
    private LocalDate date;
    private LocalTime startTime;
}
