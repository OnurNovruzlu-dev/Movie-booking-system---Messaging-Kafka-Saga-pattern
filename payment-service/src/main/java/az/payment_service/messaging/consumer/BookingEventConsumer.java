package az.payment_service.messaging.consumer;

import az.payment_service.event.booking.BookingCreatedEvent;
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
public class BookingEventConsumer {

    private final PaymentEventHandler paymentEventHandler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "booking-created",
            groupId = "payment-service-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeBookingCreatedEvent(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                                           @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                           @Payload String value) {
        log.info("Received BookingCreatedEvent - Key: {}, Partition: {}", key, partition);

        try {
            BookingCreatedEvent event = objectMapper.readValue(value, BookingCreatedEvent.class);

            log.info("Processing booking: {} for user: {}, amount: ${}, seats: {}",
                    event.getBookingId(),
                    event.getUserId(),
                    event.getAmount(),
                    event.getSeatIds());

            paymentEventHandler.handleBookingCreated(event);

            log.info("Successfully processed BookingCreatedEvent for booking: {}",
                    event.getBookingId());
        } catch (JsonProcessingException e) {
            log.error("Error processing BookingCreatedEvent - Key: {}, will retry", key, e);
        }

    }

}
