package az.payment_service.messaging.consumer;

import az.payment_service.event.SeatReservationFailedEvent;
import az.payment_service.service.PaymentEventHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeatEventConsumer {

    private final PaymentEventHandler eventHandler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "seat-reservation-failed",
            groupId = "payment-service-group",
            containerFactory = "kafkaListenerFactory")
    public void consumeSeatReservationFailedEvent(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                                  @Payload String value) {
        log.info("SeatReservationFailed Event consuming. key: {}, partition: {}", key, partition);

        try {
            SeatReservationFailedEvent event = objectMapper.readValue(value, SeatReservationFailedEvent.class);

            eventHandler.handleSeatReservationFailedEvent(event);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
