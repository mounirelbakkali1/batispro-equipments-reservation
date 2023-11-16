package ma.youcode.RentalHive.repository;

import ma.youcode.RentalHive.domain.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
