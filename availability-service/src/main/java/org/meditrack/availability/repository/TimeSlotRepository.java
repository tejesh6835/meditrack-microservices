package org.meditrack.availability.repository;

import org.meditrack.availability.entity.Availability;
import org.meditrack.availability.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByDoctorAvailability_Id(Long doctorAvailabilityId);

    List<TimeSlot> findByDoctorAvailability_IdAndDate(Long doctorId, LocalDate date);

    List<TimeSlot> findByDoctorAvailability_IdAndAvailabilityAndDate(Long doctorId, Availability availability, LocalDate date);

    Boolean existsByDoctorAvailability_IdAndStartTimeAndDateAndAvailability(Long doctorId, LocalTime startTime, LocalDate date, Availability availability);

    Availability getByDoctorAvailability_IdAndStartTimeAndDate(Long doctorId, LocalTime startTime, LocalDate date);

    TimeSlot findByDoctorAvailability_IdAndStartTimeAndDate(Long doctorId, LocalTime startTime, LocalDate date);
}
