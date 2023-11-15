package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;

public interface IEquipmentService {

    EquipmentResponseDTO createEquipment (EquipmentCreationRequestDTO equipment);

}
