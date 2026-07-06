package ra.demo.service;

import ra.demo.model.dto.request.TicketRequest;
import ra.demo.model.dto.response.TicketResponse;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest request);

    TicketResponse checkOut(Long vehicleId);
}
