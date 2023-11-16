package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.LocationStatus;
import ma.youcode.RentalHive.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "reservations")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer quantity;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private LocationStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @ManyToOne
    private EquipmentUnit equipmentUnit;
}
