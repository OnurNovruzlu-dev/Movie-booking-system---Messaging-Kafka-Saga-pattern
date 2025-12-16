package az.payment_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentFailedEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long bookingId;
    private String reason;

    public PaymentFailedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentFailedEvent(Long bookingId, String reason, String correlationId) {
        this();
        this.bookingId = bookingId;
        this.reason = reason;
        this.correlationId = correlationId;
    }
}
