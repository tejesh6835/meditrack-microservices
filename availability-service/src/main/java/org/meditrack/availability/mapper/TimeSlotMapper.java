package org.meditrack.availability.mapper;

import org.meditrack.availability.dto.TimeSlotDto;
import org.meditrack.availability.entity.TimeSlot;

public class TimeSlotMapper {

    public static TimeSlot toEntity(TimeSlotDto dto) {
        if (dto == null) {
            return null;
        }
        return TimeSlot.builder()
                .doctorId(dto.getDoctorId())
                .doctorName(dto.getDoctorName())
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .slotDurationMins(dto.getSlotDurationMins())
                .availability(dto.getAvailability())
                .build();
    }

    public static TimeSlotDto toDto(TimeSlot entity) {
        if (entity == null) {
            return null;
        }
        return TimeSlotDto.builder()
                .doctorId(entity.getDoctorId())
                .doctorName(entity.getDoctorName())
                .date(entity.getDate())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .slotDurationMins(entity.getSlotDurationMins())
                .availability(entity.getAvailability())
                .build();
    }

}