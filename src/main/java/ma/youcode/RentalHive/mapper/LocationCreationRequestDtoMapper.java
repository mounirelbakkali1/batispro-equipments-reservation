package ma.youcode.RentalHive.mapper;

import ma.youcode.RentalHive.domain.entity.Location;
import ma.youcode.RentalHive.dto.locationDTO.LocationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class LocationCreationRequestDtoMapper {


    public LocationRequestDto mapToDto(Location location){
        return LocationRequestDto.builder()
                .reference(location.getReference())
                .status(location.getStatus().toString())
                .startDate(location.getStartDate())
                .endDate(location.getEndDate())
                .quantity(location.getQuantity())
                .equipmentReference(location.getEquipment().getModel())
                .paymentStatus(location.getPaymentStatus()==null?null:location.getPaymentStatus().toString())
                .build();
    }
}
