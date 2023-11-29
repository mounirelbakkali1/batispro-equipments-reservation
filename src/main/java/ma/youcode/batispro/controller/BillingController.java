package ma.youcode.batispro.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.youcode.batispro.dto.BillDto;
import ma.youcode.batispro.dto.BillObject;
import ma.youcode.batispro.exception.BillNotFoundException;
import ma.youcode.batispro.exception.DossierNotFoundException;
import ma.youcode.batispro.service.IBillingService;
import ma.youcode.batispro.service.IContractGenerator;
import ma.youcode.batispro.service.IContractService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/billing")
@RequiredArgsConstructor
public class BillingController {

    private final IBillingService billingService;
    private final IContractService contractService;


    @PostMapping("/create")
    public ResponseEntity<BillDto> createBilling(@RequestParam("d-number") String dossierNumber,@RequestBody @Valid BillObject billObject) throws DossierNotFoundException, URISyntaxException {
        BillDto bill = billingService.createBill(dossierNumber,billObject);
        return ResponseEntity.created(
                new URI("/api/v1/billing/create")
        )
                .eTag("/api/v1/billing/create/"+bill.billNumber())
                .body(bill);
    }

    @GetMapping
    public ResponseEntity<List<BillDto>> getAllBills(@RequestParam(defaultValue = "0",required = false) int page, @RequestParam(defaultValue = "10",required = false) int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        List<BillDto> allBills = billingService.getAllBills(pageable);
        return ResponseEntity.ok(allBills);
    }

    @GetMapping(value = "/{billNumber}/contract",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> createContract(@PathVariable String billNumber) throws Exception {
        byte[] contract = contractService.createContract(billNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=contract"+billNumber+".pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(contract.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(contract);
    }

}
