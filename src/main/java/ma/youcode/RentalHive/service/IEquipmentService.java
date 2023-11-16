package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentType;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentResponseDTO;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentUpdateRequestDTO;

import java.util.List;

public interface IEquipmentService {

    EquipmentResponseDTO createEquipment(EquipmentCreationRequestDTO equipment);
    EquipmentResponseDTO updateEquipment(EquipmentUpdateRequestDTO equipmentUpdateRequestDTO);
    EquipmentResponseDTO getEquipmentById(Long id);

    List<EquipmentResponseDTO> getAllEquipments();
    void deleteEquipmentById(Long id) ;
    List<EquipmentResponseDTO> searchEquipment(String name, EquipmentType equipmentType, EquipmentStatus equipmentStatus);
}
