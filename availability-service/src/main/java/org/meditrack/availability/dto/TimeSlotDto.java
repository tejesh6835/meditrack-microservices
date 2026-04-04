package org.meditrack.availability.dto;

import lombok.*;
import org.meditrack.availability.entity.Availability;
import org.meditrack.availability.entity.DoctorAvailability;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TimeSlotDto implements Serializable {

    Long doctorId;
    String doctorName;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Integer slotDurationMins;
    Availability availability;
}
