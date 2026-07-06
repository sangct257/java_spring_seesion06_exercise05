package ra.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.demo.model.Vehicle;
import ra.demo.model.dto.response.VehicleResponse;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("""
        SELECT new ra.demo.model.dto.response.VehicleResponse(
            v.id,
            v.licensePlate,
            v.color,
            v.type
        )
        FROM Vehicle v
        WHERE
            (:keyword IS NULL
            OR
            LOWER(v.licensePlate) LIKE LOWER(CONCAT('%',:keyword,'%')))
        """)
    Page<VehicleResponse> findAllByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
