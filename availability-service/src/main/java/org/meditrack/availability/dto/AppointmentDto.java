package org.meditrack.availability.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppointmentDto implements Serializable {
    private String doctorName;
    private LocalDate date;
    private LocalTime startTime;
}
