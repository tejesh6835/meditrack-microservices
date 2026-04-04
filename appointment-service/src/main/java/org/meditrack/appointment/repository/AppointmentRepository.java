package org.meditrack.appointment.repository;

import lombok.NonNull;
import org.meditrack.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<@NonNull Appointment, Long> {

}
