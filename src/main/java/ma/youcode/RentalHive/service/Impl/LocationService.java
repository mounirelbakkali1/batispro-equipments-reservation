package ma.youcode.RentalHive.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.RentalHive.domain.entity.*;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.Location.LocationFolderStatus;
import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;
import ma.youcode.RentalHive.domain.enums.UserRole;
import ma.youcode.RentalHive.dto.clientDTO.ClientDossierRequestDto;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentResponseDTO;
import ma.youcode.RentalHive.dto.locationDTO.*;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.repository.LocationFolderRepository;
import ma.youcode.RentalHive.repository.LocationRepository;
import ma.youcode.RentalHive.service.ILocationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;
    private final LocationFolderRepository locationFolderRepository;
    private final EquipmentRepository equipmentRepository;
    private final LocationCreationRequestDtoMapper locationCreationRequestDtoMapper;

    @Override
    public LocationFolderDetailsDto createLocationFolder(@Valid LocationCreationRequestDto locationRequest) {
        boolean anyInvalidDateForLocation = locationRequest.locationRequests().stream()
                .anyMatch(lr -> lr.startDate().isBefore(LocalDateTime.now()) ||
                        lr.endDate().isBefore(lr.startDate()));
        if (anyInvalidDateForLocation) {
            throw new IllegalArgumentException("Invalid date for location");
        }

        List<Location> locationsRequest = new ArrayList<>();
        locationRequest.locationRequests()
                .stream()
                .forEach(lr -> {
                    Equipment equipment = equipmentRepository.findByModel(lr.equipmentReference())
                            .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));
                    List<EquipmentUnit> equipmentUnits = equipment.getEquipmentUnits();
                    long availableEquipmentUnits = equipmentUnits.stream()
                            .filter(eu -> eu.getEquipmentStatus().equals(EquipmentStatus.valueOf(lr.status())))
                            .count();

                    if (availableEquipmentUnits < lr.quantity()) {
                        throw new IllegalArgumentException("Not enough equipment");
                    }
                    // lastly
                    Location location = Location.builder()
                            .quantity(lr.quantity())
                            .startDate(lr.startDate())
                            .endDate(lr.endDate())
                            .build();
                    locationsRequest.add(location);
                });

        String uniqueDossierNumber = "D" + LocalDateTime.now();
        ClientDossierRequestDto clientDossierRequestDto = locationRequest.clientDossierRequestDto();
         Client client = new Client(
                String.join(" ", clientDossierRequestDto.firstName(), clientDossierRequestDto.lastName()),
                clientDossierRequestDto.email(),
                    UserRole.ROLE_CLIENT,
                true,
                clientDossierRequestDto.address(),
                clientDossierRequestDto.phone(),
                clientDossierRequestDto.cin()
        );
        DossierLocation dossierLocation = DossierLocation.builder()
                .dossierNumber(uniqueDossierNumber)
                .location(locationsRequest)
                .client(client)
                .status(LocationFolderStatus.PENDING)
                .build();
        locationFolderRepository.save(dossierLocation);
        return LocationFolderDetailsDto.builder()
                .locationDetails(
                        new LocationDetailsDto(LocationFolderStatus.PENDING, locationRequest)
                ).build();
    }

    @Override
    public LocationFolderDetailsDto consultLocationFolder(String dossierNumber) {
        Optional<DossierLocation> byDossierNumber = locationFolderRepository.findByDossierNumber(dossierNumber);
        DossierLocation dossierLocation = byDossierNumber.orElseThrow(() -> new IllegalArgumentException("Dossier not found"));
        List<Location> locationList = dossierLocation.getLocation();
        List<LocationRequestDto> locationRequestDtos = locationList
                .stream()
                .map(locationCreationRequestDtoMapper::mapToDto)
                .toList();
        LocationDetailsDto locationDetailsDto = LocationDetailsDto.builder()
                .locationRequest(
                        LocationCreationRequestDto.builder()
                                .locationRequests(
                                        locationRequestDtos
                                ).build()
                )
                .status(dossierLocation.getStatus())
                .build();
        return LocationFolderDetailsDto.builder()
                .locationDetails(
                       locationDetailsDto
                ).dateSubmission(dossierLocation.getDateCreation().toString())
                .build();
    }

    @Override
    public LocationFolderDetailsDto acceptLocationFolder(String dossierNumber) {
        return null;
    }

    @Override
    public LocationFolderDetailsDto resolveLocationFolder(String locationFolderNumber, LocationDetailsDto locationDetails) {
        return null;
    }

    @Override
    public List<LocationFolderDetailsDto> findAllLocationFolders() {
        return null;
    }

    @Override
    public List<LocationDetailsDto> findAllLocations() {
        try {
            return locationRepository.findAll().stream()
                    .map(LocationDetailsDto::locationDetailsDto)
                    .collect(Collectors.toList());
        }catch (DataAccessException e){
            log.error("Error occurred during fetching all locations", e);
            throw new RuntimeException("Failed to fetch locations", e);
        }
    }

    @Override
    public List<LocationDetailsDto> findAllLocationsByStatus(String status) {
        return null;
    }
}
