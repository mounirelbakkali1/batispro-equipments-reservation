package ma.youcode.RentalHive.dto.locationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocationRequestDto (
        @NotEmpty String equipmentReference,
        @NotEmpty Integer quantity,
        @NotEmpty LocalDate startDate,
        @NotEmpty LocalDate endDate,
        @NotEmpty String status,
        @NotEmpty String paymentStatus
){
}
