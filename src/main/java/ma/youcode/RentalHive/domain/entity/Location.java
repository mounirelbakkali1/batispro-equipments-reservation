package ma.youcode.RentalHive.domain.entity;

import lombok.*;
import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;
import ma.youcode.RentalHive.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "reservations")
@Getter
@Setter
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private LocationStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @ManyToOne()
    @JoinTable(name = "equipment_unit_location",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_unit_id"))
    private EquipmentUnit equipmentUnit;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private DossierLocation dossierLocation;
}
