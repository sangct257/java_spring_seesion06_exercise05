package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.demo.model.dto.request.TicketRequest;
import ra.demo.model.dto.response.ApiResponse;
import ra.demo.model.dto.response.TicketResponse;
import ra.demo.service.ParkingService;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Check in thanh cong",
                parkingService.checkIn(request),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(
            @PathVariable Long vehicleId){

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Check out thanh cong ",
                parkingService.checkOut(vehicleId),
                null,
                HttpStatus.OK
        ),HttpStatus.OK);

    }
}
