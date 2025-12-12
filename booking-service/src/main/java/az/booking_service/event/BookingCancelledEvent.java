package az.booking_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BookingCancelledEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long bookingId;
    private String reason;

    public BookingCancelledEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    public BookingCancelledEvent(Long bookingId, String correlationId, String reason) {
        this();
        this.correlationId = correlationId;
        this.bookingId = bookingId;
        this.reason = reason;
    }
}
