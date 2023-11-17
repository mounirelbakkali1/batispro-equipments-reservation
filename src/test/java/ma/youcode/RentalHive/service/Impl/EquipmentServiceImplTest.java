package ma.youcode.RentalHive.service.Impl;

import ma.youcode.RentalHive.service.Impl.EquipmentServiceImpl;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentResponseDTO;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.domain.entity.Equipment;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EquipmentServiceImplTest {

    @InjectMocks
    private EquipmentServiceImpl equipmentServiceImpl;

    @Mock
    private EquipmentRepository equipmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_deleteEquipment_success(){
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(new Equipment()));
        equipmentServiceImpl.deleteEquipmentById(1L);
        Mockito.verify(equipmentRepository, Mockito.times(1)).deleteById(1L);
    }
}
