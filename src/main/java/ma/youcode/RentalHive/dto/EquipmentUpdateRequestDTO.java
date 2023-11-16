package ma.youcode.RentalHive.dto;

import ma.youcode.RentalHive.domain.entity.Equipment;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;

import javax.validation.constraints.NotNull;

public record EquipmentUpdateRequestDTO(
                                        @NotNull String name,
                                        @NotNull String model,
                                        @NotNull EquipmentType equipmentType,
                                        @NotNull String description,
                                        @NotNull EquipmentStatus equipmentStatus) {
    public static Equipment equipmentFromEquipmentUpdateRequestDTO(long id, EquipmentUpdateRequestDTO equipmentUpdateRequestDTO){
        return new Equipment(id,
                equipmentUpdateRequestDTO.name,
                equipmentUpdateRequestDTO.equipmentType,
                equipmentUpdateRequestDTO.model,
                equipmentUpdateRequestDTO.description,
                equipmentUpdateRequestDTO.equipmentStatus);
    }
}
