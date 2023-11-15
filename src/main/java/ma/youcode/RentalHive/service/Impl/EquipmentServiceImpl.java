package ma.youcode.RentalHive.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.RentalHive.domain.entity.Equipment;
import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.service.IEquipmentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EquipmentServiceImpl implements IEquipmentService {

    private final EquipmentRepository equipmentRepository;


    @Override
    public EquipmentResponseDTO createEquipment(EquipmentCreationRequestDTO equipmentDTO) {
        try {
          Equipment equipment =  equipmentRepository.save(EquipmentCreationRequestDTO.equipmentFromEquipmentCreationRequestDTO(equipmentDTO));
          log.info("Equipment created succefully");
          return EquipmentResponseDTO.fromEquipment(equipment);
        }catch (DataAccessException e){
            log.error("Error occured during saving equipment", e);
            throw new RuntimeException("Failed to save equipment" , e);
        }

    }
}
