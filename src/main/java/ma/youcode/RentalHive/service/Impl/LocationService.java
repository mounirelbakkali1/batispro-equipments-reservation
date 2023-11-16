package ma.youcode.RentalHive.service.Impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.RentalHive.domain.entity.*;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.Location.LocationFolderStatus;
import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;
import ma.youcode.RentalHive.domain.enums.UserRole;
import ma.youcode.RentalHive.dto.clientDTO.ClientDossierRequestDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationDetailsDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationFolderDetailsDto;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.repository.LocationFolderRepository;
import ma.youcode.RentalHive.repository.LocationRepository;
import ma.youcode.RentalHive.service.ILocationService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;
    private final LocationFolderRepository locationFolderRepository;
    private final EquipmentRepository equipmentRepository;

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
                            .filter(eu -> eu.getEquipmentStatus().equals(EquipmentStatus.NEW))
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
                        new LocationDetailsDto(LocationStatus.PENDING, locationRequest)
                ).build();
    }

    @Override
    public LocationFolderDetailsDto consultLocationFolder(String dossierNumber) {
        return null;
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
        return null;
    }

    @Override
    public List<LocationDetailsDto> findAllLocationsByStatus(String status) {
        return null;
    }
}
