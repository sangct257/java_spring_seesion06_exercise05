package ra.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSummaryResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}
