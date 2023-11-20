package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;
import ma.youcode.RentalHive.dto.LocationStatusUpdateDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationDetailsDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationFolderDetailsDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationRequestDto;
import ma.youcode.RentalHive.exception.DossierNotFoundException;

import java.util.List;

public interface ILocationService {
    public LocationFolderDetailsDto createLocationFolder(LocationCreationRequestDto locationRequest);
    public LocationFolderDetailsDto consultLocationFolder(String dossierNumber) throws DossierNotFoundException;

    public LocationFolderDetailsDto acceptLocationFolder(String dossierNumber) throws DossierNotFoundException;

    public LocationFolderDetailsDto resolveLocationFolder(String locationFolderNumber,  List<LocationStatusUpdateDto> statusUpdates) throws DossierNotFoundException;

    public List<LocationFolderDetailsDto> findAllLocationFolders();

    public List<LocationRequestDto> findAllLocations();

    public List<LocationRequestDto> findAllLocationsByStatus(LocationStatus status);
}
