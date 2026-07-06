package ra.demo.service;

import ra.demo.model.Vehicle;
import ra.demo.model.dto.request.VehicleCreateRequest;
import ra.demo.model.dto.response.PageResponse;
import ra.demo.model.dto.response.VehicleResponse;

public interface VehicleService {
    PageResponse<VehicleResponse> getPagedVehicles(
            Integer page,
            Integer size,
            String sortBy,
            String direction,
            String keyword
    );

    Vehicle createVehicle(VehicleCreateRequest request);
}
