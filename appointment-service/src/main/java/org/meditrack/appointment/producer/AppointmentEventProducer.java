package org.meditrack.appointment.producer;

import lombok.RequiredArgsConstructor;
import org.meditrack.appointment.dto.AppointmentCreatedEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class AppointmentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishAppointmentCreated(AppointmentCreatedEventDto event) {
        kafkaTemplate.send("appointment.events", event.getAppointmentId(), event);
    }
}