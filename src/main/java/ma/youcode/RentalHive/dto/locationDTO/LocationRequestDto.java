package ma.youcode.RentalHive.dto.locationDTO;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Builder
public record LocationRequestDto (
        @NotEmpty String equipmentReference,
        @NotEmpty Integer quantity,
        @NotEmpty LocalDate startDate,
        @NotEmpty LocalDate endDate,
        @NotEmpty String status
){
}
