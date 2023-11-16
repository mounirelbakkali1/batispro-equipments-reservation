package ma.youcode.RentalHive.domain.entity;

import lombok.*;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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


    public Equipment(Long id, String name, EquipmentType equipmentType, String model, String description, EquipmentStatus equipmentStatus) {
        this.id = id;
        this.name = name;
        this.equipmentType = equipmentType;
        this.model = model;
        this.description = description;
        this.equipmentStatus = equipmentStatus;
    }

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<EquipmentUnit> equipmentUnits;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", equipmentType=" + equipmentType +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", equipmentStatus=" + equipmentStatus +
                '}';
    }
}
