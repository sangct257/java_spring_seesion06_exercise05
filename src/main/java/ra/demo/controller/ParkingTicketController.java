package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.demo.model.dto.response.ApiResponse;
import ra.demo.model.dto.response.TicketSummaryResponse;
import ra.demo.service.ParkingTicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;


    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> summary() {

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách vé trong ngày thành công",
                parkingTicketService.getTodayTickets(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);

    }

}
