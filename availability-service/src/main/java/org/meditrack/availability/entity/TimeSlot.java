package org.meditrack.availability.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "time_slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    Long doctorId;
    String doctorName;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Integer slotDurationMins;
    @Enumerated(EnumType.STRING)
    Availability availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_availability_id")
    DoctorAvailability doctorAvailability;
}
