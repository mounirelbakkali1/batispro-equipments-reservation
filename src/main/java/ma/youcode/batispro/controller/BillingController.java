package ma.youcode.batispro.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.batispro.dto.BillDto;
import ma.youcode.batispro.dto.BillObject;
import ma.youcode.batispro.exception.DossierNotFoundException;
import ma.youcode.batispro.service.IBillingService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/billing")
@RequiredArgsConstructor
public class BillingController {

    private final IBillingService billingService;


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

}
