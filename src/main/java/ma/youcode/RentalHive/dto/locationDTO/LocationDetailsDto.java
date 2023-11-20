package ma.youcode.RentalHive.dto.locationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ma.youcode.RentalHive.domain.entity.Location;
import ma.youcode.RentalHive.domain.enums.Location.LocationFolderStatus;
import ma.youcode.RentalHive.domain.enums.Location.LocationStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LocationDetailsDto(
        LocationFolderStatus status,
        LocationCreationRequestDto locationRequest
) {
    public static LocationDetailsDto locationDetailsDto(Location location){
        return null;
    }
}
