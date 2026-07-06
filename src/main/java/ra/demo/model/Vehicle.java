package ra.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
}
