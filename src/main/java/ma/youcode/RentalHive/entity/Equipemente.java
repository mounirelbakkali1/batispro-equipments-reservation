package ma.youcode.RentalHive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.enums.EquipementStatus;
import ma.youcode.RentalHive.enums.EquipementType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Equipemente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private EquipementType equipementType;
    private String model;
    private String description ;
    private EquipementStatus equipementStatus;

}
