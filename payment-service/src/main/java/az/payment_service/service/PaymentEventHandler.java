package az.payment_service.service;

import az.payment_service.event.PaymentFailedEvent;
import az.payment_service.event.PaymentProcessedEvent;
import az.payment_service.event.PaymentRefundedEvent;
import az.payment_service.event.SeatReservationFailedEvent;
import az.payment_service.event.booking.BookingCreatedEvent;
import az.payment_service.mapper.ConvertToEntity;
import az.payment_service.messaging.producer.PaymentEventProducer;
import az.payment_service.model.Payment;
import az.payment_service.model.PaymentStatus;
import az.payment_service.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

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
                    "Payment amount exceeds limit"));
        } else {

            paymentRepository.save(payment);

            log.info("PaymentProcessedEvent published. BookingId: {}", event.getBookingId());

            paymentEventProducer.publishPaymentProcessedEvent(new PaymentProcessedEvent(event));
        }
    }


    public void handleSeatReservationFailedEvent(SeatReservationFailedEvent event) {
        log.info("Payment refunding. bookingId : {}", event.getBookingId());

        Payment payment = paymentRepository.findByBookingIdAndStatus(event.getBookingId(), PaymentStatus.PROCESSED);
        payment.setStatus(PaymentStatus.REFUNDED);
        // some refund codes ... (refund amount to customer account)

        paymentRepository.save(payment);

        log.info("PaymentRefunded Event will publish");

        paymentEventProducer.publishPaymentRefundedEvent(new PaymentRefundedEvent(event.getBookingId()));
    }
}
