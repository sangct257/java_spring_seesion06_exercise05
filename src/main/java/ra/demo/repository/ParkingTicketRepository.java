package ra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.demo.model.ParkingTicket;
import ra.demo.model.dto.response.TicketSummaryResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("""
        SELECT new ra.demo.model.dto.response.TicketSummaryResponse(
            p.id,
            p.vehicle.licensePlate,
            p.zone.name,
            p.checkInTime,
            p.checkOutTime
        )
        FROM ParkingTicket p
        WHERE FUNCTION('DATE', p.checkInTime) = CURRENT_DATE
        """)
    List<TicketSummaryResponse> findTodayTickets();

}
