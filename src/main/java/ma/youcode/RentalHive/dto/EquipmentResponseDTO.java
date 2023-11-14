package ma.youcode.RentalHive.dto;

import ma.youcode.RentalHive.entity.Equipment;
import ma.youcode.RentalHive.enums.EquipementStatus;
import ma.youcode.RentalHive.enums.EquipementType;

public record EquipmentResponseDTO(
        Long id,
        String name,
        EquipementType equipementType,
        String model,
        String description,
        EquipementStatus equipementStatus
) {
    public static EquipmentResponseDTO fromEquipment (Equipment equipment){
        return new EquipmentResponseDTO(
                equipment.getId(),
                equipment.getName(),
                equipment.getEquipementType(),
                equipment.getModel(),
                equipment.getDescription(),
                equipment.getEquipementStatus()

        );
    }


}
