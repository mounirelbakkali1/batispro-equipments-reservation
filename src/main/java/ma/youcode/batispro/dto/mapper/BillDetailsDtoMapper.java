package ma.youcode.batispro.dto.mapper;


import ma.youcode.batispro.domain.entity.BillDetails;
import ma.youcode.batispro.domain.entity.Location;
import ma.youcode.batispro.dto.BillDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class BillDetailsDtoMapper {

    public BillDetailsDto mapToBillDetailsDto(BillDetails billDetails){
        return BillDetailsDto.builder()
                .equipmentPrice(billDetails.getPriceUnit().toString())
                .equipmentQuantity(billDetails.getQuantity().toString())
                .totalPrice(billDetails.getTotalPrice().toString())
                .build();


    }
}
