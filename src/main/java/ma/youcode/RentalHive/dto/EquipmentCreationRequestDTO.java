package ma.youcode.RentalHive.dto;

import ma.youcode.RentalHive.domain.entity.Equipment;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;

import javax.validation.constraints.NotNull;

public record EquipmentCreationRequestDTO(
        @NotNull
        String name,
        @NotNull
        String model,
        @NotNull
        EquipmentType equipmentType,
        @NotNull
        String description,
        @NotNull
        EquipmentStatus equipmentStatus

) {
        public static Equipment equipmentFromEquipmentCreationRequestDTO (EquipmentCreationRequestDTO equipment){
         return new Equipment(

                 null,
                 equipment.name,
                 equipment.equipmentType,
                 equipment.model,
                 equipment.description,
                 equipment.equipmentStatus

         );
        }
}
