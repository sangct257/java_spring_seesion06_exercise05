package ra.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private String checkInTime;
    private String checkOutTime;
}
