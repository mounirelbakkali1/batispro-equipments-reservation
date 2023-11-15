package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private EquipmentType equipmentType;
    private String model;
    private String description ;
    private EquipmentStatus equipmentStatus;

}
