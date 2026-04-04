package org.meditrack.availability.repository;

import lombok.NonNull;
import org.meditrack.availability.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface DoctorAvailabilityRepository extends JpaRepository<@NonNull DoctorAvailability, @NonNull Long> {
    boolean existsByDoctorNameAndDate(String doctorName, LocalDate date);

    Optional<DoctorAvailability> findByDoctorNameAndDate(String doctorName, LocalDate date);

    Optional<DoctorAvailability> findByDoctorName(String doctorName);


}
