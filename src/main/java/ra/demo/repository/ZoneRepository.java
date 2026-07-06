package ra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.demo.model.Zone;
import ra.demo.model.dto.response.ZoneStatisticsResponse;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    @Query("""
        SELECT new ra.demo.model.dto.response.ZoneStatisticsResponse(
            z.id,
            z.name,
            z.capacity,
            z.occupiedSpots,
            (z.capacity - z.occupiedSpots)
        )
        FROM Zone z
        """)
    List<ZoneStatisticsResponse> getStatistics();
}
