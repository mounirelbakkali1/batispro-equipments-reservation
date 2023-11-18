package ma.youcode.RentalHive.dto.locationDTO;

import lombok.RequiredArgsConstructor;
import ma.youcode.RentalHive.domain.entity.DossierLocation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationFolderDetailsDtoMapper {

    private final LocationCreationRequestDtoMapper locationCreationRequestDtoMapper;



    public LocationFolderDetailsDto mapToDto(DossierLocation dossierLocation){
        List<LocationRequestDto> locationRequestDtos = dossierLocation.getLocation()
                .stream()
                .map(locationCreationRequestDtoMapper::mapToDto)
                .toList();
        return LocationFolderDetailsDto.builder()
                .dateSubmission(dossierLocation.getDateCreation().toString())
                .locationDetails(
                        LocationDetailsDto.builder()
                                .locationRequest(
                                        LocationCreationRequestDto.builder()
                                                .locationRequests(locationRequestDtos
                                                ).build()
                                ).status(dossierLocation.getStatus())
                                .build()
                )
                .build();
    }
}
