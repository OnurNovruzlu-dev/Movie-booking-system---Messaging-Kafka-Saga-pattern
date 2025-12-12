package az.booking_service.messaging.producer;

import az.booking_service.event.BookingCancelledEvent;
import az.booking_service.event.BookingConfirmedEvent;
import az.booking_service.event.BookingCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishBookingCreatedEvent(BookingCreatedEvent event) {
        try {
            String topic = "booking-created";
            String key = event.getBookingId().toString();
            String value = objectMapper.writeValueAsString(event);

            log.info("Publishing BookingCreatedEvent to topic: {} with key: {}", topic, key);

            kafkaTemplate.send(topic, key, value);

        } catch (Exception e) {
            log.error("Error serializing BookingCreatedEvent", e);
            throw new RuntimeException("Failed to publish booking created event", e);
        }
    }

//    public void publishBookingConfirmedEvent(BookingConfirmedEvent event) {
//        try {
//            String topic = "booking-confirmed";
//            String key = event.getBookingId().toString();
//            String value = objectMapper.writeValueAsString(event);
//
//            log.info("Publishing BookingConfirmedEvent for booking: {}", key);
//
//            kafkaTemplate.send(topic, key, value);
//
//        } catch (Exception e) {
//            log.error("Error serialization BookingConfirmedEvent", e);
//            throw new RuntimeException("Failed to publish booking confirmed event", e);
//        }
//    }
//
//    public void publishBookingCancelledEvent(BookingCancelledEvent event) {
//        try {
//            String topic = "booking-cancelled";
//            String key = event.getBookingId().toString();
//            String value = objectMapper.writeValueAsString(event);
//
//            log.info("Publishing BookingCancelledEvent for booking: {}, reason: {}", key, event.getReason());
//
//            kafkaTemplate.send(topic, key, value);
//
//        } catch (Exception e) {
//            log.error("Error serialization BookingCancelledEvent", e);
//            throw new RuntimeException("Failed to publish booking cancelled event", e);
//        }
//    }
}
