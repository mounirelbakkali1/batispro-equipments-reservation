package ma.youcode.batispro.dto;

import javax.validation.constraints.NotBlank;

public record BillObject(
        @NotBlank String object,
        @NotBlank String description
) {
}
