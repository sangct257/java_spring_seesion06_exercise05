package ra.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "occupied_spots", nullable = false)
    private Integer occupiedSpots;

    @OneToMany(mappedBy = "zone")
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
}
