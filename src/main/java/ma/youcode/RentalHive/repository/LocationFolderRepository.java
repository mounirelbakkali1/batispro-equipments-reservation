package ma.youcode.RentalHive.repository;

import ma.youcode.RentalHive.domain.entity.DossierLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationFolderRepository extends JpaRepository<DossierLocation, Long> {
}
