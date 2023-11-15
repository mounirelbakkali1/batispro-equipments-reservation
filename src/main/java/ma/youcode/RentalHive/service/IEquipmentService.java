package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;

import java.util.List;

public interface IEquipmentService {

    EquipmentResponseDTO createEquipment (EquipmentCreationRequestDTO equipment);
    EquipmentResponseDTO getEquipmentById(Long id);
    List<EquipmentResponseDTO> getAllEquipments();
    void deleteEquipmentById(Long id) ;
}
