package ma.youcode.RentalHive.dto.locationDTO;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
public record LocationRequestDto (
        @NotEmpty String equipmentReference,
        @NotEmpty Integer quantity,
        @NotEmpty LocalDateTime startDate,
        @NotEmpty LocalDateTime endDate,
        @NotEmpty String status
){
}
