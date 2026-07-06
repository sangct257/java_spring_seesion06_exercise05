package ra.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.model.dto.response.TicketSummaryResponse;
import ra.demo.repository.ParkingTicketRepository;
import ra.demo.service.ParkingTicketService;

import java.util.List;

@Service
public class ParkingTicketServiceInpl implements ParkingTicketService {
    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Override
    public List<TicketSummaryResponse> getTodayTickets() {
        return parkingTicketRepository.findTodayTickets();
    }
}
