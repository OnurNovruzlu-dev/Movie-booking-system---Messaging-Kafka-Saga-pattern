package az.booking_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BookingConfirmedEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long bookingId;

    public BookingConfirmedEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    public BookingConfirmedEvent(Long bookingId, String correlationId) {
        this();
        this.correlationId = correlationId;
        this.bookingId = bookingId;
    }
}
