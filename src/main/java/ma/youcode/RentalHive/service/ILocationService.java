package ma.youcode.RentalHive.service;

import ma.youcode.RentalHive.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationDetailsDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationFolderDetailsDto;

import java.util.List;

public interface ILocationService {
    public LocationFolderDetailsDto createLocationFolder(LocationCreationRequestDto locationRequest);
    public LocationFolderDetailsDto consultLocationFolder(String dossierNumber);

    public LocationFolderDetailsDto acceptLocationFolder(String dossierNumber);

    public LocationFolderDetailsDto resolveLocationFolder(String locationFolderNumber, LocationDetailsDto locationDetails);

    public List<LocationFolderDetailsDto> findAllLocationFolders();

    public List<LocationDetailsDto> findAllLocations();

    public List<LocationDetailsDto> findAllLocationsByStatus(String status);
}
