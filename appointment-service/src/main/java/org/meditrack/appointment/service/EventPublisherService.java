package org.meditrack.appointment.service;

import lombok.RequiredArgsConstructor;
import org.meditrack.appointment.dto.AppointmentCreatedEventDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisherService {

    private final ApplicationEventPublisher publisher;

    public void publishEvent(AppointmentCreatedEventDto event) {
        publisher.publishEvent(event);
    }

}
