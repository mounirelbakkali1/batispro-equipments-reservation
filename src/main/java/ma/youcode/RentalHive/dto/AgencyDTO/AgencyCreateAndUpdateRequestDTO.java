package ma.youcode.RentalHive.dto.AgencyDTO;

import ma.youcode.RentalHive.domain.entity.Agency;
import ma.youcode.RentalHive.domain.entity.Users;

import javax.validation.constraints.NotNull;

public record AgencyCreateAndUpdateRequestDTO(@NotNull String local, @NotNull Boolean is_enabled, @NotNull Users user) {
    public static Agency agencyFromAgencyCreateDTO(AgencyCreateAndUpdateRequestDTO agency){
        return new Agency(
                null,
                agency.local,
                agency.is_enabled,
                agency.user
        );
    }

    public static Agency agencyFromAgencyUpdateDTO(Long id, AgencyCreateAndUpdateRequestDTO agency){
        return new Agency(
                id,
                agency.local,
                agency.is_enabled,
                agency.user
        );
    }
}
