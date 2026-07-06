package ra.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.demo.model.Vehicle;
import ra.demo.model.dto.request.VehicleCreateRequest;
import ra.demo.model.dto.response.PageResponse;
import ra.demo.model.dto.response.VehicleResponse;
import ra.demo.repository.VehicleRepository;
import ra.demo.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(Integer page, Integer size, String sortBy, String direction, String keyword) {
        if (page < 0){
            page = 0;
        }

        Pageable pageable;
        if (sortBy == null || sortBy.isBlank()
                || direction == null || direction.isBlank()
        ){
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort = direction.equalsIgnoreCase("ASC")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            pageable = PageRequest.of(page, size, sort);
        }

        if (keyword == null && !keyword.isBlank()){
            keyword = null;
        }

        Page<VehicleResponse> pageResult =
                vehicleRepository.findAllByKeyword(keyword,pageable);

        return PageResponse.<VehicleResponse>builder()
                .items(pageResult.getContent())
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalItems(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .isLast(pageResult.isLast())
                .build();
    }

    @Override
    public Vehicle createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .type(request.getType())
                .build();
        return vehicleRepository.save(vehicle);
    }
}
