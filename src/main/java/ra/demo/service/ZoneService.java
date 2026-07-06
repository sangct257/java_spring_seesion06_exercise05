package ra.demo.service;

import ra.demo.model.dto.response.ZoneStatisticsResponse;

import java.util.List;

public interface ZoneService {
    List<ZoneStatisticsResponse> getStatisticsV1();

    List<ZoneStatisticsResponse> getStatisticsV2();
}
