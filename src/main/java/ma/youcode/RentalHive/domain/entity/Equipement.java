package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.EquipementStatus;
import ma.youcode.RentalHive.domain.enums.EquipementType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Equipement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private EquipementType equipementType;
    private String model;
    private String description ;
    private EquipementStatus equipementStatus;

}
