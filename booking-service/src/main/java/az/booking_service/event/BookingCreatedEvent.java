package az.booking_service.event;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BookingCreatedEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long bookingId;
    private String userId;
    private String movieId;
    private List<String> seatIds;
    private BigDecimal amount;

    public BookingCreatedEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    public BookingCreatedEvent(Long bookingId, String userId, String movieId,
                               List<String> seatIds, BigDecimal amount) {
        this();
        this.correlationId = UUID.randomUUID().toString(); // New saga instance
        this.bookingId = bookingId;
        this.userId = userId;
        this.movieId = movieId;
        this.seatIds = seatIds;
        this.amount = amount;
    }

}
