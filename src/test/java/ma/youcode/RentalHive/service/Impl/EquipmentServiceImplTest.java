package ma.youcode.RentalHive.service.Impl;

import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.Equipment.EquipmentType;
import ma.youcode.RentalHive.dto.equipmentDTO.EquipmentResponseDTO;
import ma.youcode.RentalHive.exception.EquipmentNotFoundException;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.domain.entity.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    public void test_deleteEquipment_notFound() {
        when(equipmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EquipmentNotFoundException.class, () -> {
            equipmentServiceImpl.deleteEquipmentById(1L);
        });
    }
    /* @Test
        public void test_deleteEquipment_dataAccessException() {
        when(equipmentRepository.findById(any())).thenThrow(DataAccessException.class);
        assertThrows(RuntimeException.class, () -> {
            equipmentServiceImpl.deleteEquipmentById(1L);
        });
    }

   */
    @Test
    public void test_getEquipmentById_success() {

        Equipment mockEquipment = new Equipment(1L, "Equipment 1", EquipmentType.EXCAVATOR, "Model 1", "Description 1", EquipmentStatus.NEW);
        Mockito.when(equipmentRepository.findById(1L)).thenReturn(Optional.of(mockEquipment));

        EquipmentResponseDTO result = equipmentServiceImpl.getEquipmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Equipment 1", result.name());
        assertEquals(EquipmentType.EXCAVATOR, result.equipmentType());
        assertEquals("Model 1", result.model());
        assertEquals("Description 1", result.description());
        assertEquals(EquipmentStatus.NEW, result.equipmentStatus());
    }
    @Test
    public void test_getEquipmentById_notFound() {
        Mockito.when(equipmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EquipmentNotFoundException.class, () -> equipmentServiceImpl.getEquipmentById(1L));
    }

    @Test
    public void test_getEquipmentById_dataAccessException() {
        Mockito.when(equipmentRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> equipmentServiceImpl.getEquipmentById(1L));
    }
}
