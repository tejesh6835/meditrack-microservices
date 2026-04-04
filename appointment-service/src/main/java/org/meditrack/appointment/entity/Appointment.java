package org.meditrack.appointment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private String patientName;
    private LocalDate date;
    private LocalTime startTime;
    @Enumerated(EnumType.STRING)
    private Status status;

    LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
