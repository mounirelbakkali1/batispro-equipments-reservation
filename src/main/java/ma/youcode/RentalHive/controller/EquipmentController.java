package ma.youcode.RentalHive.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;
import ma.youcode.RentalHive.service.IEquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/equipments")
@RequiredArgsConstructor
@Slf4j
@RestController
public class EquipmentController {

    private final IEquipmentService  equipmentService;


    @GetMapping({"/",""})
    public ResponseEntity<List<EquipmentResponseDTO>> getAllEquipment(){
        log.info("Request received to retrieve all equipments");
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }


    @PostMapping("/create")
    public ResponseEntity<EquipmentResponseDTO> createEquipment(@Valid @RequestBody EquipmentCreationRequestDTO requestDTO){
        log.info("Request received to create a new equipment.");
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(requestDTO));
    }
    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResponseDTO> getEquipmentById(@PathVariable Long equipmentId){
        log.info("Request received to get equipment by ID: {}", equipmentId);
        return ResponseEntity.ok(equipmentService.getEquipmentById(equipmentId));
    }
}
