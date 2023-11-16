package ma.youcode.RentalHive.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.RentalHive.dto.LocationCreationRequestDto;
import ma.youcode.RentalHive.dto.LocationFolderDetailsDto;
import ma.youcode.RentalHive.service.ILocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

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
}
