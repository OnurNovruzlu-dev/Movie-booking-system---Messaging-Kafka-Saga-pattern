package az.payment_service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SeatReservationFailedEvent {
    private String eventId;
    private LocalDateTime timestamp;

    private Long bookingId;
}
