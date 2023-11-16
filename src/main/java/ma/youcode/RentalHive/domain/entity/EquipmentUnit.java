package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class EquipmentUnit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    @Enumerated(EnumType.STRING)
    private EquipmentStatus equipmentStatus;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;


    @Override
    public String toString() {
        return "EquipmentUnit{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", equipmentStatus=" + equipmentStatus +
                ", quantity=" + quantity +
                '}';
    }
}
