package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;
import ma.youcode.RentalHive.dto.EquipmentUpdateRequestDTO;

import java.util.List;

public interface IEquipmentService {

    EquipmentResponseDTO createEquipment(EquipmentCreationRequestDTO equipment);
    EquipmentResponseDTO updateEquipment(EquipmentUpdateRequestDTO equipmentUpdateRequestDTO);
    EquipmentResponseDTO getEquipmentById(Long id);

    List<EquipmentResponseDTO> getAllEquipments();
}
