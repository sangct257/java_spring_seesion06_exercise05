package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.demo.model.dto.request.VehicleCreateRequest;
import ra.demo.model.dto.response.ApiResponse;
import ra.demo.model.dto.response.PageResponse;
import ra.demo.model.dto.response.VehicleResponse;
import ra.demo.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestBody VehicleCreateRequest request){

        vehicleService.createVehicle(request);

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Thêm xe thành công",
                null,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword){

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Đã lấy danh sách thành công",
                vehicleService.getPagedVehicles(page,size,sortBy,direction,keyword),
                null,
                HttpStatus.OK
        ),HttpStatus.OK);

    }

}
