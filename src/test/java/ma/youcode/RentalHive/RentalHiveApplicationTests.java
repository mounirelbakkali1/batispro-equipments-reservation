package ma.youcode.RentalHive;

import ma.youcode.RentalHive.domain.entity.Equipment;
import ma.youcode.RentalHive.domain.enums.EquipmentStatus;
import ma.youcode.RentalHive.domain.enums.EquipmentType;
import ma.youcode.RentalHive.dto.EquipmentCreationRequestDTO;
import ma.youcode.RentalHive.dto.EquipmentResponseDTO;
import ma.youcode.RentalHive.repository.EquipmentRepository;
import ma.youcode.RentalHive.service.Impl.EquipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentalHiveApplicationTests {
	private Equipment equipment;

	@Mock
	private EquipmentRepository equipmentRepository;

	@InjectMocks
	private EquipmentServiceImpl equipmentService;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateEquipment() {
		EquipmentCreationRequestDTO requestDTO = new EquipmentCreationRequestDTO("name", "model", EquipmentType.BETONNIERE,"description", EquipmentStatus.AVAILABLE);
		Equipment equipment = new Equipment();
		Mockito.doNothing().when(equipmentRepository).save(equipment);
		EquipmentResponseDTO responseDTO = equipmentService.createEquipment(requestDTO);
		assertNotNull(responseDTO);

	}

}
