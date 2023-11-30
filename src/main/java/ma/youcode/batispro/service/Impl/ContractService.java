package ma.youcode.batispro.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.domain.enums.BillStatus;
import ma.youcode.batispro.exception.ContractCreationException;
import ma.youcode.batispro.service.IBillingService;
import ma.youcode.batispro.service.IContractGenerator;
import ma.youcode.batispro.service.IContractService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractService implements IContractService {

    private final IBillingService billingService;
    private final IContractGenerator contractGenerator;



    @Override
    public byte[] createContract(String billNumber) throws Exception {
        Bill bill = billingService.getBillByNumber(billNumber);
        if(!bill.getStatus().equals(BillStatus.ACCEPTED)) {
            throw new ContractCreationException("Cannot generate contract for bill with status: " + bill.getStatus());
        }
        log.info("Generating contract for bill: {}", billNumber);
        return contractGenerator.generateContract(bill);
    }
}
