package az.payment_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentFailedEvent {
    private String eventId;
    private LocalDateTime timestamp;

    private Long bookingId;
    private String reason;

    public PaymentFailedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentFailedEvent(Long bookingId, String reason) {
        this();
        this.bookingId = bookingId;
        this.reason = reason;
    }
}
