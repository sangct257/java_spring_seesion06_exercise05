package ra.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.demo.model.VehicleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;

    private String licensePlate;

    private String color;

    private VehicleType type;
}
