package ma.youcode.batispro.dto;

import lombok.Builder;

@Builder
public record BillDetailsDto(
        String equipmentPrice,
        String equipmentQuantity,
        String totalPrice
) {
}
