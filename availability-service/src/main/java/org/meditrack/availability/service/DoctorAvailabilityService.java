package org.meditrack.availability.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.meditrack.availability.dto.AppointmentDto;
import org.meditrack.availability.dto.DoctorAvailabilityDto;
import org.meditrack.availability.dto.TimeSlotDto;
import org.meditrack.availability.entity.Availability;
import org.meditrack.availability.entity.DoctorAvailability;
import org.meditrack.availability.entity.TimeSlot;
import org.meditrack.availability.exception.*;
import org.meditrack.availability.mapper.DoctorAvailabilityMapper;
import org.meditrack.availability.mapper.TimeSlotMapper;
import org.meditrack.availability.repository.DoctorAvailabilityRepository;
import org.meditrack.availability.repository.TimeSlotRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {

    private final Logger logger = Logger.getLogger(DoctorAvailabilityService.class.getName());

    private final TimeSlotRepository timeSlotRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Transactional
    public List<TimeSlotDto> fillDoctorAvailability(DoctorAvailabilityDto doctorAvailabilityDto, Authentication authentication)
    {

        String doctorName = authentication.getName();
        doctorAvailabilityDto.setDoctorName(doctorName);

        validateRequest(doctorAvailabilityDto);


        DoctorAvailability doctorAvailability = DoctorAvailabilityMapper.toEntity(doctorAvailabilityDto);

        doctorAvailabilityRepository.save(doctorAvailability);

        Long doctorId = doctorAvailability.getId();
        LocalDate date = doctorAvailability.getDate();
        LocalTime startTime = doctorAvailability.getStartTime();
        LocalTime endTime = doctorAvailability.getEndTime();
        Integer slotDurationMins = doctorAvailability.getSlotDurationMins();

        List<TimeSlotDto> timeSlots = new LinkedList<>();
        //while (slotStartTime.plusMinutes(slotDurationMins).isBefore(endTime)||slotStartTime.plusMinutes(slotDurationMins).equals(endTime))
        while (!startTime.plusMinutes(slotDurationMins).isAfter(endTime))
        {
            TimeSlotDto timeSlotDto = new TimeSlotDto();
            timeSlotDto.setDoctorId(doctorId);
            timeSlotDto.setDate(date);
            timeSlotDto.setStartTime(startTime);
            timeSlotDto.setDoctorName(doctorName);
            startTime = startTime.plusMinutes(slotDurationMins);

            timeSlotDto.setEndTime(startTime);
            timeSlotDto.setAvailability(Availability.AVAILABLE);
            timeSlotDto.setSlotDurationMins(slotDurationMins);

            TimeSlot timeSlot = TimeSlotMapper.toEntity(timeSlotDto);

            timeSlot.setDoctorAvailability(doctorAvailability);
            timeSlotRepository.save(timeSlot);
            timeSlots.add(timeSlotDto);
        }


        return timeSlots;
    }

    private void validateRequest(DoctorAvailabilityDto doctorAvailabilityDto)
    {
        logger.info("Validating doctor availability request "+ doctorAvailabilityDto);

        if(doctorAvailabilityDto.getDate() == null ||
                doctorAvailabilityDto.getStartTime() == null ||
                doctorAvailabilityDto.getEndTime() == null ||
                doctorAvailabilityDto.getSlotDurationMins() == null)
        {
            throw new EmptyRequestReceived("Doctor availability details cannot be null");
        }


        LocalTime startTimeCheck = doctorAvailabilityDto.getStartTime();
        LocalTime endTimeCheck = doctorAvailabilityDto.getEndTime();
        if(startTimeCheck.isAfter(endTimeCheck)||startTimeCheck.equals(endTimeCheck))
        {
            throw new StartTimeBeforeEndTimeException("Start time should be before end time");
        }

        String doctorName = doctorAvailabilityDto.getDoctorName();
        doctorAvailabilityRepository
                .findByDoctorNameAndDate(doctorName, doctorAvailabilityDto.getDate())
                .ifPresent(existing -> {
                    throw new AlreadyRegisteredException(
                            "Doctor " + doctorName +
                                    " has already registered from " +
                                    existing.getStartTime() + " to " +
                                    existing.getEndTime() + " on " +
                                    existing.getDate());
                });
    }

    public List<TimeSlotDto> getMySlots(LocalDate date, Authentication authentication) {

        String doctorName = authentication.getName();
//        Boolean doctorAlreadyExists = doctorAvailabilityRepository.existsByDoctorNameAndDate(doctorName, date);
//
//        if(!doctorAlreadyExists)
//        {
//            throw new DoctorNotExistsException("Doctor Not found for the date "+ date);
//        }
//
//            DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findByDoctorNameAndDate(doctorName,date).orElse(null);
        DoctorAvailability doctorAvailability =
                doctorAvailabilityRepository
                        .findByDoctorNameAndDate(doctorName, date)
                        .orElseThrow(() ->
                                new DoctorNotExistsException(
                                        "Doctor Not found for the date " + date));
            Long doctorId= doctorAvailability.getId();

            List<TimeSlot> timeSlots =
                    timeSlotRepository.findByDoctorAvailability_IdAndDate(doctorId,date);

        logger.info("Doctor Slots "+ timeSlots);

        return timeSlots.stream().map(TimeSlotMapper::toDto).toList();
    }

    @Cacheable(value="doctorAvailability", key="'availability:' + #doctorName + ':' + #date")
    public List<TimeSlotDto> getAvailableSlots(String doctorName, LocalDate date) {

//        boolean doctorAlreadyExists = doctorAvailabilityRepository.existsByDoctorNameAndDate(doctorName, date);
//
//        if(!doctorAlreadyExists)
//        {
//            throw new DoctorNotExistsException("Doctor Not found for the date "+ date);
//        }
//
//        DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findByDoctorNameAndDate(doctorName,date).orElse(null);

        DoctorAvailability doctorAvailability =
                doctorAvailabilityRepository
                        .findByDoctorNameAndDate(doctorName, date)
                        .orElseThrow(() ->
                                new DoctorNotExistsException(
                                        "Doctor Not found for the date " + date));
        Long doctorId= doctorAvailability.getId();

        List<TimeSlot> timeSlots =
                timeSlotRepository.findByDoctorAvailability_IdAndAvailabilityAndDate(doctorId, Availability.AVAILABLE,date);
        logger.info("Doctor Slots "+ timeSlots);

        return timeSlots.stream().map(TimeSlotMapper::toDto).toList();
    }

//    public Availability checkSlotsExists(AppointmentDto appointmentDto) {
//        String doctorName = appointmentDto.getDoctorName();
//        LocalDate date = LocalDate.parse(appointmentDto.getDate());
//        LocalTime startTime = appointmentDto.getStartTime();
//        DoctorAvailability doctorAvailability =
//                doctorAvailabilityRepository
//                        .findByDoctorNameAndDate(doctorName, date)
//                        .orElseThrow(() ->
//                                new DoctorNotExistsException(
//                                        "Doctor Not found for the date " + date));
//        Long doctorId= doctorAvailability.getId();
//        LocalTime endTime = doctorAvailability.getEndTime();
//        if(startTime.isAfter(endTime)||startTime.equals(endTime))
//        {
//            throw new DoctorNotAvailableAtThatTime("Doctor not available at that time "+ startTime);
//        }
//        TimeSlot timeSlot=timeSlotRepository.findByDoctorAvailability_IdAndStartTimeAndDate(doctorId,startTime,date);
//        return timeSlot.getAvailability();
//    }

    @Transactional
    @CacheEvict(value="doctorAvailability",
            key="'availability:' + #appointmentDto.doctorName + ':' + #appointmentDto.date")
    public Availability setSlotBooked(AppointmentDto appointmentDto) {
        String doctorName = appointmentDto.getDoctorName();
        LocalDate date = appointmentDto.getDate();
        LocalTime startTime = appointmentDto.getStartTime();

        DoctorAvailability doctorAvailability =
                doctorAvailabilityRepository
                        .findByDoctorNameAndDate(doctorName, date)
                        .orElseThrow(() ->
                                new DoctorNotExistsException(
                                        "Doctor Not found for the date " + date));
        Long doctorId= doctorAvailability.getId();
        LocalTime endTime = doctorAvailability.getEndTime();
        if(startTime.isAfter(endTime)||startTime.equals(endTime))
        {
            throw new DoctorNotAvailableAtThatTime("Doctor not available at that time "+ startTime);
        }

        TimeSlot timeSlot=timeSlotRepository.findByDoctorAvailability_IdAndStartTimeAndDate(doctorId,startTime,date);

        if (timeSlot == null) {
            throw new DoctorNotAvailableAtThatTime(
                    "No slot found at time " + startTime);
        }

        if (timeSlot.getAvailability() == Availability.BOOKED) {
            throw new DoctorAlreadyBookedException(
                    "Doctor already booked for time " + startTime);
        }
        timeSlot.setAvailability(Availability.BOOKED);
        return timeSlot.getAvailability();
    }

    @CacheEvict(value="doctorAvailability",
            key="'availability:' + #appointmentDto.doctorName + ':' + #appointmentDto.date")
    @Transactional
    public void releaseSlots(AppointmentDto appointmentDto) {
        String doctorName = appointmentDto.getDoctorName();
        LocalDate date = appointmentDto.getDate();
        LocalTime startTime = appointmentDto.getStartTime();

        DoctorAvailability doctorAvailability =
                doctorAvailabilityRepository
                        .findByDoctorNameAndDate(doctorName, date)
                        .orElseThrow(() ->
                                new DoctorNotExistsException(
                                        "Doctor Not found for the date " + date));
        Long doctorId= doctorAvailability.getId();
        LocalTime endTime = doctorAvailability.getEndTime();
        if(startTime.isAfter(endTime)||startTime.equals(endTime))
        {
            throw new DoctorNotAvailableAtThatTime("Doctor not available at that time "+ startTime);
        }

        TimeSlot timeSlot=timeSlotRepository.findByDoctorAvailability_IdAndStartTimeAndDate(doctorId,startTime,date);

        if (timeSlot == null) {
            throw new DoctorNotAvailableAtThatTime(
                    "No slot found at time " + startTime);
        }

        timeSlot.setAvailability(Availability.AVAILABLE);
    }
}
