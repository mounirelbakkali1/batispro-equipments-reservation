package ma.youcode.RentalHive.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.RentalHive.domain.entity.Agency;
import ma.youcode.RentalHive.dto.AgencyDTO.AgencyCreateAndUpdateRequestDTO;
import ma.youcode.RentalHive.dto.AgencyDTO.AgencyResponseDTO;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentResponseDTO;
import ma.youcode.RentalHive.exception.EquipmentNotFoundException;
import ma.youcode.RentalHive.repository.AgencyRepository;
import ma.youcode.RentalHive.service.IAgencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgencyServiceImpl implements IAgencyService {
    public final AgencyRepository agencyRepository;

    @Override
    public List<AgencyResponseDTO> getAllAgency() {
        return agencyRepository.findAll().stream()
                .map(AgencyResponseDTO::fromAgency)
                .collect(Collectors.toList());
    }


    @Override
    public AgencyResponseDTO getAgencyById(Long id) {
        Objects.requireNonNull(id, "Equipment ID must not be null");
        return agencyRepository.findById(id)
                .map(AgencyResponseDTO::fromAgency)
                .orElseThrow(()->new IllegalArgumentException(String.format("Agency with id %d not found", id)));
    }

    @Override
    public AgencyResponseDTO createAgency(AgencyCreateAndUpdateRequestDTO agency) {
        Agency agency1 = agencyRepository.save(AgencyCreateAndUpdateRequestDTO.agencyFromAgencyCreateDTO(agency));
        log.info("Agency created successfully");
        return AgencyResponseDTO.fromAgency(agency1);
    }

    @Override
    public AgencyResponseDTO updateAgency(Long id, AgencyCreateAndUpdateRequestDTO agency) {
        Agency agency1 = agencyRepository.save(AgencyCreateAndUpdateRequestDTO.agencyFromAgencyUpdateDTO(id, agency));
        log.info("Agency updated successfully");
        return AgencyResponseDTO.fromAgency(agency1);
    }

    @Override
    public void deleteAgencyById(Long id) {
        Objects.requireNonNull(id, "Agency ID must not be null");
        if(agencyRepository.findById(id).isPresent()){
            agencyRepository.deleteById(id);
            log.info(String.format("Agency with id %d deleted successfully", id));
        }else{
            throw new EquipmentNotFoundException(String.format("Agency with id %d not found", id));
        }
    }
}
