package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;
import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;

import java.util.List;

public interface IEquipmentService {

    EquipmentResponseDTO createEquipment (EquipmentCreationRequestDTO equipment);
    EquipmentResponseDTO getEquipmentById(Long id);
    List<EquipmentResponseDTO> getAllEquipments();
    void deleteEquipmentById(Long id) ;
    List<EquipmentResponseDTO> searchEquipment(String name, EquipmentType equipmentType, EquipmentStatus equipmentStatus);
}
