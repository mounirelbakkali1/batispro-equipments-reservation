package ma.youcode.RentalHive.dto.locationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ma.youcode.RentalHive.dto.clientDTO.ClientDossierRequestDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LocationCreationRequestDto(
        @NotEmpty List<@NotNull LocationRequestDto> locationRequests,
        @NotNull ClientDossierRequestDto clientDossierRequestDto
        ) {
}
