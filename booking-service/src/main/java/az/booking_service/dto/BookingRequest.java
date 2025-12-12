package az.booking_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {
    @NotNull
    private String userId;
    @NotNull
    private String movieId;
    @NotEmpty
    private List<String> seatIds;
    @NotNull
    private BigDecimal amount;
}
