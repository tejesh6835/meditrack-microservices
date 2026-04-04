package org.meditrack.availability.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "doctor_availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String doctorName;
    @NonNull
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Integer slotDurationMins;

}
