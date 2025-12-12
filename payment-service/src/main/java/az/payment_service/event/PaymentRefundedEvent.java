package az.payment_service.event;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentRefundedEvent {
    private String eventId;
    private String correlationId;
    private LocalDateTime timestamp;

    private Long id;
    private Long bookingId;
    private BigDecimal amount;
    private String currency;

    public PaymentRefundedEvent() {
        eventId = UUID.randomUUID().toString();
        timestamp = LocalDateTime.now();
    }

    public PaymentRefundedEvent(Long id, Long bookingId, BigDecimal amount,
                                String currency, String correlationId) {
        this();
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.currency = currency;
        this.correlationId = correlationId;
    }
}
