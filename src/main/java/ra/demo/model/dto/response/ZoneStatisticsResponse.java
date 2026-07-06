package ra.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneStatisticsResponse {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer occupiedSlots;
    private Integer availableSlots;
}
