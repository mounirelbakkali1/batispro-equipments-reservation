package ma.youcode.RentalHive.dto.locationDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ma.youcode.RentalHive.dto.locationDTO.LocationDetailsDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LocationFolderDetailsDto(
    String dateSubmission,
    String validatedBy,
    String folderNumber,
    LocationDetailsDto locationDetails
) {
}
