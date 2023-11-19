package ma.youcode.RentalHive.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.RentalHive.dto.locationDTO.LocationCreationRequestDto;
import ma.youcode.RentalHive.dto.locationDTO.LocationFolderDetailsDto;
import ma.youcode.RentalHive.exception.DossierNotFoundException;
import ma.youcode.RentalHive.service.ILocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final ILocationService locationService;

    @PostMapping
    public ResponseEntity<LocationFolderDetailsDto> createLocation(@RequestBody @Valid LocationCreationRequestDto locationCreationRequestDto) throws URISyntaxException {
        LocationFolderDetailsDto locationFolder = locationService.createLocationFolder(locationCreationRequestDto);
        return ResponseEntity
                .created(new URI("/v1/locations"))
                .body(locationFolder);
    }
    @PostMapping("/folder/{folderNumber}")
    public ResponseEntity<LocationFolderDetailsDto> consultLocationFolder(@NotBlank @PathVariable String folderNumber) throws DossierNotFoundException {
        LocationFolderDetailsDto locationFolder = locationService.consultLocationFolder(folderNumber);
        return ResponseEntity.ok(locationFolder);
    }

    @PostMapping("/folder/{folderNumber}/validate")
    public ResponseEntity<LocationFolderDetailsDto> validateLocationFolder(@NotBlank @PathVariable String folderNumber) throws DossierNotFoundException {
        LocationFolderDetailsDto locationFolder = locationService.acceptLocationFolder(folderNumber);
        return ResponseEntity.ok(locationFolder);
    }

    @GetMapping("/folder")
    public ResponseEntity<List<LocationFolderDetailsDto>> getAllLocationFolders(){
        List<LocationFolderDetailsDto> allLocationFolders = locationService.findAllLocationFolders();
        return ResponseEntity.ok(allLocationFolders);
    }


}
