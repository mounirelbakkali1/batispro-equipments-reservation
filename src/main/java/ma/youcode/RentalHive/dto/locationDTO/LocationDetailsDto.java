package ma.youcode.RentalHive.dto.locationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationDetailsDto(
        LocationStatus status,
        LocationCreationRequestDto locationRequest
) {
}
