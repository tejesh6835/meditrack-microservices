package org.meditrack.notification.consumer;

import lombok.RequiredArgsConstructor;
import org.meditrack.notification.dto.AppointmentCreatedEventDto;
import org.meditrack.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "appointment.events", groupId = "notification-group")
    public void consume(AppointmentCreatedEventDto event) {

        if ("APPOINTMENT_CREATED".equals(event.getEventType())) {
            notificationService.sendNotification(event);
        }
    }
}