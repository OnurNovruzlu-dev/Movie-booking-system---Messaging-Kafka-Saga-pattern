package az.payment_service.messaging.producer;

import az.payment_service.event.PaymentFailedEvent;
import az.payment_service.event.PaymentProcessedEvent;
import az.payment_service.event.PaymentRefundedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publishPaymentProcessedEvent(PaymentProcessedEvent event) {

    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {

    }

    public void publishPaymentRefundedEvent(PaymentRefundedEvent event) {

    }
}
