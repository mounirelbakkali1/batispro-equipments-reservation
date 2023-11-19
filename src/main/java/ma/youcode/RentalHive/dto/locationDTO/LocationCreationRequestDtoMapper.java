package ma.youcode.RentalHive.dto.locationDTO;

import ma.youcode.RentalHive.domain.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationCreationRequestDtoMapper {


    public LocationRequestDto mapToDto(Location location){
        return LocationRequestDto.builder()
                .status(location.getStatus().toString())
                .startDate(location.getStartDate())
                .endDate(location.getEndDate())
                .quantity(location.getQuantity())
                .equipmentReference(location.getEquipment().getModel())
                .paymentStatus(location.getPaymentStatus()==null?null:location.getPaymentStatus().toString())
                .build();
    }
}
