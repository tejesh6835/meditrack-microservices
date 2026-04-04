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
public class DoctorAvailabilityDto implements Serializable {
    String doctorName;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Integer slotDurationMins;
}
