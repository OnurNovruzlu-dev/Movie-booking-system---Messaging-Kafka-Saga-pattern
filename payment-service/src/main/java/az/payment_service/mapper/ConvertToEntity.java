package az.payment_service.mapper;

import az.payment_service.event.booking.BookingCreatedEvent;
import az.payment_service.model.Payment;
import az.payment_service.model.PaymentStatus;

public class ConvertToEntity {

    public static Payment bookingCreatedEvent(BookingCreatedEvent event) {
        Payment payment = new Payment();
        payment.setBookingId(event.getBookingId());
        payment.setAmount(event.getAmount());
        payment.setStatus(PaymentStatus.PROCESSED);

        return payment;
    }
}
