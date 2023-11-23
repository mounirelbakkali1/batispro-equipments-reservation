package ma.youcode.batispro.dto.mapper;

import lombok.RequiredArgsConstructor;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.dto.BillDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillDtoMapper {

    private final BillDetailsDtoMapper billDetailsDtoMapper;


    public BillDto mapToDto(Bill bill){
        return BillDto.builder()
                .billNumber(bill.getBillNumber())
                .object(bill.getObject())
                .description(bill.getDescription())
                .creationDate(bill.getDateCreation().toString())
                .billStatus(bill.getStatus().toString())
                .billTotal(bill.getTotal().toString())
                .billPaymentStatus(bill.getPaymentStatus().toString())
                .clientEmail(bill.getClient().getEmail())
                .clientName(bill.getClient().getName())
                .dossierNumber(bill.getDossierLocation().getDossierNumber())
                .billDetails(bill.getBillDetails().stream()
                        .map(billDetailsDtoMapper::mapToBillDetailsDto)
                        .toList())
                .build();
    }
}
