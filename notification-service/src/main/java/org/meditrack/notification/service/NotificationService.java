package org.meditrack.notification.service;

import lombok.RequiredArgsConstructor;
import org.meditrack.notification.dto.AppointmentCreatedEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(AppointmentCreatedEventDto event) {

        log.info("Sending notification to patient: {} for appointment with {}", event.getPatientName(), event.getDoctorName());
    }
}
