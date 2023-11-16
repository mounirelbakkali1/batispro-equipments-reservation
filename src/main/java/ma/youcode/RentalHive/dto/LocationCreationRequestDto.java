package ma.youcode.RentalHive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationCreationRequestDto(
        @NotEmpty List<@NotNull LocationRequestDto> locationRequests,
        @NotNull ClientDossierRequestDto clientDossierRequestDto
        ) {
}
