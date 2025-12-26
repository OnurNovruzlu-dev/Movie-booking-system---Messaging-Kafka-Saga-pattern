package az.payment_service.messaging.producer;

import az.payment_service.event.PaymentFailedEvent;
import az.payment_service.event.PaymentProcessedEvent;
import az.payment_service.event.PaymentRefundedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;


    public void publishPaymentProcessedEvent(PaymentProcessedEvent event) {
        try {
            String topic = "payment-processed";
            String key = event.getBookingId().toString();
            String value = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topic, key, value);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {
        try {
            String topic = "payment-failed";
            String key = event.getBookingId().toString();
            String value = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topic, key, value);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishPaymentRefundedEvent(PaymentRefundedEvent event) {
        try {
            String topic = "payment-refunded";
            String key = event.getBookingId().toString();
            String value = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topic, key, value);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
