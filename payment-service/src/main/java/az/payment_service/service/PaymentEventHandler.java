package az.payment_service.service;

import az.payment_service.event.PaymentFailedEvent;
import az.payment_service.event.PaymentProcessedEvent;
import az.payment_service.event.booking.BookingCreatedEvent;
import az.payment_service.mapper.ConvertToEntity;
import az.payment_service.messaging.producer.PaymentEventProducer;
import az.payment_service.model.Payment;
import az.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventHandler {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    public void handleBookingCreated(BookingCreatedEvent event) {
        // let's say customer's balance is 1000
        BigDecimal customersBalance = BigDecimal.valueOf(1000);

        Payment payment = ConvertToEntity.bookingCreatedEvent(event);

        if (payment.getAmount().compareTo(customersBalance) > 0) {

            log.info("PaymentFailedEvent published. BookingId: {}", event.getBookingId());

            paymentEventProducer.publishPaymentFailedEvent(new PaymentFailedEvent(
                    event.getBookingId(),
                    "Payment amount exceeds limit",
                    event.getCorrelationId()));
        } else {

            payment = paymentRepository.saveAndFlush(payment);

            log.info("PaymentProcessedEvent published. Id : {}, BookingId: {}", payment.getId(), event.getBookingId());

            paymentEventProducer.publishPaymentProcessedEvent(new PaymentProcessedEvent(
                    payment.getId(),
                    event.getBookingId(),
                    event.getCorrelationId()
            ));
        }
    }
}
