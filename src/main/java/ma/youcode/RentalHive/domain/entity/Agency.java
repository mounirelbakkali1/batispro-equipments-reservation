package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Agency {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    @Column(columnDefinition = "BIT(1) DEFAULT 1")
    private Boolean is_enabled;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Users users;
}
