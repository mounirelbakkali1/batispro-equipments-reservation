package ma.youcode.RentalHive.repository;

import ma.youcode.RentalHive.domain.entity.DossierLocation;
import ma.youcode.RentalHive.dto.locationDTO.LocationFolderDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationFolderRepository extends JpaRepository<DossierLocation, Long> {
    Optional<DossierLocation> findByDossierNumber(String dossierNumber);
}
