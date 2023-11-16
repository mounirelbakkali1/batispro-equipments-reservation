package ma.youcode.RentalHive.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record LocationRequestDto (
        @NotEmpty String equipmentReference,
        @NotEmpty Integer quantity,
        @NotEmpty LocalDateTime startDate,
        @NotEmpty LocalDateTime endDate,
        @NotEmpty String status
){
}
