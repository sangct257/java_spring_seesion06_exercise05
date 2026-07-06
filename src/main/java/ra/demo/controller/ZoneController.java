package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.demo.model.dto.response.ApiResponse;
import ra.demo.model.dto.response.ZoneStatisticsResponse;
import ra.demo.service.ZoneService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @GetMapping("/v1/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getV1() {

        long start = System.currentTimeMillis();

        List<ZoneStatisticsResponse> result =
                zoneService.getStatisticsV1();

        long end = System.currentTimeMillis();

        System.out.println("API V1: " + (end - start) + " ms");

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy thống kê thành công",
                result,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);

    }

    @GetMapping("/v2/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getV2() {

        long start = System.currentTimeMillis();

        List<ZoneStatisticsResponse> result =
                zoneService.getStatisticsV2();

        long end = System.currentTimeMillis();

        System.out.println("API V2: " + (end - start) + " ms");

        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy thống kê thành công",
                result,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);

    }

}
