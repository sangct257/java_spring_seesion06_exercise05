package ra.demo.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.model.ParkingTicket;
import ra.demo.model.Vehicle;
import ra.demo.model.Zone;
import ra.demo.model.dto.request.TicketRequest;
import ra.demo.model.dto.response.TicketResponse;
import ra.demo.repository.ParkingTicketRepository;
import ra.demo.repository.VehicleRepository;
import ra.demo.repository.ZoneRepository;
import ra.demo.service.ParkingService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public TicketResponse checkIn(TicketRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(() ->
                new NoSuchElementException("Vehicle khong ton tai"));
        Zone zone = zoneRepository.findById(request.getZoneId()).orElseThrow(() ->
                new NoSuchElementException("Zone khong ton tai"));
        if (zone.getOccupiedSpots() > zone.getCapacity()) {
            throw new NoSuchElementException("Khu vuc da day");
        }
        ParkingTicket parkingTicket = ParkingTicket.builder()
                .vehicle(vehicle)
                .zone(zone)
                .checkInTime(LocalDateTime.now())
                .build();
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);

        parkingTicketRepository.save(parkingTicket);

        zoneRepository.save(zone);
        return TicketResponse.builder()
                .id(parkingTicket.getId())
                .licensePlate(parkingTicket.getVehicle().getLicensePlate())
                .zoneName(parkingTicket.getZone().getName())
                .checkInTime(String.valueOf(parkingTicket.getCheckInTime()))
                .checkOutTime(String.valueOf(parkingTicket.getCheckOutTime()))
                .build();
    }

    @Transactional
    @Override
    public TicketResponse checkOut(Long vehicleId) {

        ParkingTicket parkingTicket =
                parkingTicketRepository
                        .findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                        .orElseThrow(() ->
                                new RuntimeException("Không tìm thấy vé"));

        parkingTicket.setCheckOutTime(LocalDateTime.now());

        Zone zone = parkingTicket.getZone();

        zone.setOccupiedSpots(zone.getOccupiedSpots()-1);

        parkingTicketRepository.save(parkingTicket);

        zoneRepository.save(zone);

        return TicketResponse.builder()
                .id(parkingTicket.getId())
                .licensePlate(parkingTicket.getVehicle().getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(String.valueOf(parkingTicket.getCheckInTime()))
                .checkOutTime(String.valueOf(parkingTicket.getCheckOutTime()))
                .build();

    }
}
