package ma.youcode.RentalHive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.youcode.RentalHive.domain.enums.LocationStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationDetailsDto(
        LocationStatus status,
        LocationCreationRequestDto locationRequest
) {
}
