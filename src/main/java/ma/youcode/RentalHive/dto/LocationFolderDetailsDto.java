package ma.youcode.RentalHive.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LocationFolderDetailsDto(
    String dateSubmission,
    String validatedBy,
    LocationDetailsDto locationDetails
) {
}
