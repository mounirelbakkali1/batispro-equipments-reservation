package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class EquipmentUnit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ref;
    private EquipmentStatus equipmentStatus;
    private Integer quantity;
}
