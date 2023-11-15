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
import ma.youcode.RentalHive.exception.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class EquipmentServiceImpl implements IEquipmentService {

    private final EquipmentRepository equipmentRepository;


    @Override
    public EquipmentResponseDTO createEquipment(EquipmentCreationRequestDTO equipment) {
        try {
          Equipment equipment1 =  equipmentRepository.save(EquipmentCreationRequestDTO.equipmentFromEquipmentCreationRequestDTO(equipment));
          log.info("Equipment created succefully");
          return EquipmentResponseDTO.fromEquipment(equipment1);
        }catch (DataAccessException e){
            log.error("Error occured during saving equipment", e);
            throw new RuntimeException("Failed to save equipment" , e);
        }

    }

    @Override
    public EquipmentResponseDTO getEquipmentById(Long id) {
       try {
           return equipmentRepository.findById(id)
                   .map(EquipmentResponseDTO::fromEquipment)
                   .orElseThrow(()->new EquipmentNotFoundException(String.format("Equipment with id %d not found", id)));
       } catch(DataAccessException e){
           log.error(String.format("Error occurred during fetching equipment with id %d", id), e);
           throw new RuntimeException("Failed to fetch product", e);
        }
    }

    @Override
    public List<EquipmentResponseDTO> getAllEquipments(){
        try {
            return equipmentRepository.findAll().stream()
                    .filter(Objects::nonNull)
                    .map(EquipmentResponseDTO::fromEquipment)
                    .toList();

        }catch (DataAccessException e){
            log.error("Error occurred during fetching all equipments", e);
            throw new RuntimeException("Failed to fetch equipments", e);
        }
    }

    @Override
    public void deleteEquipmentById(Long id) {
        try{
            Objects.requireNonNull(id, "Equipment ID must not be null");
            if(equipmentRepository.findById(id).isPresent()){
                equipmentRepository.deleteById(id);
                log.info(String.format("Equipment with id %d deleted successfully", id));
            }else{
                throw new EquipmentNotFoundException(String.format("Product with id %d not found", id));
            }
        }catch(DataAccessException e){
            log.error(String.format("Error occurred during equipment deleting for id %d", id), e);
            throw new RuntimeException("Failed to delete equipment", e);
        }
    }
}
