package ra.demo.service;

import ra.demo.model.dto.response.TicketSummaryResponse;

import java.util.List;

public interface ParkingTicketService {
    List<TicketSummaryResponse> getTodayTickets();
}
