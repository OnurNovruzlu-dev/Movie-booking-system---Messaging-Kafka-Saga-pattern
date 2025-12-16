package az.payment_service.event.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingCreatedEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long bookingId;
    private String userId;
    private String movieId;
    private List<String> seatIds;
    private BigDecimal amount;
}
