package az.payment_service.event;

import az.payment_service.event.booking.BookingCreatedEvent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PaymentProcessedEvent {
    private String eventId;
    private LocalDateTime timestamp;

    private Long id;
    private Long bookingId;
    private String movieId;
    private List<String> seatIds;


    public PaymentProcessedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentProcessedEvent(BookingCreatedEvent event) {
        this();
        bookingId = event.getBookingId();
        movieId = event.getMovieId();
        seatIds = event.getSeatIds();
    }
}
