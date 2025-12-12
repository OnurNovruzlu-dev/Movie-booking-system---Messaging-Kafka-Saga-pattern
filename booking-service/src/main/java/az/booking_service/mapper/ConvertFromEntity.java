package az.booking_service.mapper;

import az.booking_service.dto.BookingResponse;
import az.booking_service.event.BookingCreatedEvent;
import az.booking_service.model.Booking;

public class ConvertFromEntity {

    public static BookingCreatedEvent bookingCreatedEvent(Booking booking) {

        return new BookingCreatedEvent(
                booking.getId(),
                booking.getUserId(),
                booking.getMovieId(),
                booking.getSeatIds(),
                booking.getAmount()
        );
    }

    public static BookingResponse bookingResponse_create(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getStatus().name(),
                "Booking created successfully. Processing payment ...",
                booking.getCorrelationId()
                );
    }
}
