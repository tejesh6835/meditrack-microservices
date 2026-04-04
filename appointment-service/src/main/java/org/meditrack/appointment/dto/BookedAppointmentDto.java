package org.meditrack.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.meditrack.appointment.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookedAppointmentDto {

    private Long id;
    private String doctorName;
    private String patientName;
    private LocalDate date;
    private LocalTime startTime;

    private Status status;

    LocalDateTime createdAt;

}
