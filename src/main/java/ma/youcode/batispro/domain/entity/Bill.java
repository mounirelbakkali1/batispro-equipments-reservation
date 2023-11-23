package ma.youcode.batispro.domain.entity;

import lombok.*;
import ma.youcode.batispro.domain.enums.BillStatus;
import ma.youcode.batispro.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billNumber;
    private String object;
    private String description;
    private LocalDateTime dateCreation;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    private PaymentStatus paymentStatus;
    private LocalDateTime dateConfirmation;
    private String comment ;


    @OneToMany(cascade = CascadeType.ALL)
    private List<BillDetails> billDetails;




    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    private DossierLocation dossierLocation;


    public Double getTotal(){
        Objects.requireNonNull(dossierLocation);
        return dossierLocation.getLocation()
                .stream()
                .map(location -> location.getEquipment().getLocationPrice() * location.getQuantity())
                .reduce(0.0, Double::sum);
    }



}
