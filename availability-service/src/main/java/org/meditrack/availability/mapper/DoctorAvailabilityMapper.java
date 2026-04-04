package org.meditrack.availability.mapper;

import org.meditrack.availability.dto.DoctorAvailabilityDto;
import org.meditrack.availability.entity.DoctorAvailability;

public class DoctorAvailabilityMapper {

    public static DoctorAvailability toEntity(DoctorAvailabilityDto dto) {
        return DoctorAvailability.builder()
                .doctorName(dto.getDoctorName())
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .slotDurationMins(dto.getSlotDurationMins())
                .build();
    }
}
