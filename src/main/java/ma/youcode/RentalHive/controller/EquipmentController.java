package ma.youcode.RentalHive.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;
import ma.youcode.RentalHive.service.IEquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1/equipments")
@RequiredArgsConstructor
@Slf4j
@RestController
public class EquipmentController {

    private final IEquipmentService  equipmentService;

    @PostMapping("/create")
    public ResponseEntity<EquipmentResponseDTO> createEquipment(@Valid @RequestBody EquipmentCreationRequestDTO requestDTO){
        log.info("Request received to create a new equipment.");
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(requestDTO));
    }
}
