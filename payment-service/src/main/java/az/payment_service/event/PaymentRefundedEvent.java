package az.payment_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentRefundedEvent {
    private String eventId;
    private LocalDateTime timestamp;

    private Long bookingId;

    public PaymentRefundedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentRefundedEvent(Long bookingId) {
        this();
        this.bookingId = bookingId;
    }
}
