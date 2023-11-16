package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.LocationFolderStatus;
import ma.youcode.RentalHive.domain.enums.LocationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class DossierLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dossierNumber ;
    @Column(name = "date_creation",columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreation ;
    @ManyToOne
    private Client client ;
    @Enumerated(EnumType.STRING)
    private LocationFolderStatus status ;
    @ManyToOne
    private Location location ;
}
