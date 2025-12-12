package az.booking_service.mapper;

import az.booking_service.dto.BookingRequest;
import az.booking_service.model.Booking;
import az.booking_service.model.BookingStatus;

import java.time.LocalDateTime;

public class ConvertToEntity {
    
    public static Booking createBookingRequest(BookingRequest request) {
        Booking booking = new Booking();

        booking.setUserId(request.getUserId());
        booking.setMovieId(request.getMovieId());
        booking.setAmount(request.getAmount());
        booking.setSeatIds(request.getSeatIds());

        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        return booking;
    }
}
