package ma.youcode.RentalHive.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.RentalHive.domain.enums.UserRole;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole UserRole;
    private boolean enable;
}
