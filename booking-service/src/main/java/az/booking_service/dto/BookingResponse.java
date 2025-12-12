package az.booking_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String userId;
    private String status;
    private String movieId;
    private List<String> seatIds;
    private BigDecimal amount;
    private String correlationId;
    private String note;

    public BookingResponse(Long bookingId, String status, String note, String correlationId) {
        this.bookingId = bookingId;
        this.status = status;
        this.note = note;
        this.correlationId = correlationId;
    }

}


