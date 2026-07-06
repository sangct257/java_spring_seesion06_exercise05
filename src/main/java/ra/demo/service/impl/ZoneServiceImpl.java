package ra.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.model.Zone;
import ra.demo.model.dto.response.ZoneStatisticsResponse;
import ra.demo.repository.ZoneRepository;
import ra.demo.service.ZoneService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public List<ZoneStatisticsResponse> getStatisticsV1() {

        List<Zone> zones = zoneRepository.findAll();

        List<ZoneStatisticsResponse> responses = new ArrayList<>();

        for (Zone zone : zones) {

            responses.add(
                    ZoneStatisticsResponse.builder()
                            .id(zone.getId())
                            .name(zone.getName())
                            .capacity(zone.getCapacity())
                            .occupiedSlots(zone.getOccupiedSpots())
                            .availableSlots(
                                    zone.getCapacity()
                                            - zone.getOccupiedSpots()
                            )
                            .build()
            );

        }

        return responses;
    }

    @Override
    public List<ZoneStatisticsResponse> getStatisticsV2() {
        return zoneRepository.getStatistics();
    }
}
