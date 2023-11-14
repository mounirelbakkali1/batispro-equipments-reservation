package ma.youcode.RentalHive.dto;

import ma.youcode.RentalHive.entity.Equipment;
import ma.youcode.RentalHive.enums.EquipementStatus;
import ma.youcode.RentalHive.enums.EquipementType;

import javax.validation.constraints.NotNull;

public record EquipmentCreationRequestDTO(
        @NotNull
        String name,
        @NotNull
        String model,
        @NotNull
        EquipementType equipementType,
        @NotNull
        String description,
        @NotNull
        EquipementStatus equipementStatus

) {
        public static Equipment equipmentFromEquipmentCreationRequestDTO (EquipmentCreationRequestDTO equipment){
         return new Equipment(

                 null,
                 equipment.name,
                 equipment.equipementType,
                 equipment.model,
                 equipment.description,
                 equipment.equipementStatus

         );
        }
}
