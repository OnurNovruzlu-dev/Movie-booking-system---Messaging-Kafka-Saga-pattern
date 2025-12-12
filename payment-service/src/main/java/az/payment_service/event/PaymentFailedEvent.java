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

    private Long id;
    private Long bookingId;

    public PaymentFailedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentFailedEvent(Long id, Long bookingId, String correlationId) {
        this();
        this.id = id;
        this.bookingId = bookingId;
        this.correlationId = correlationId;
    }
}
