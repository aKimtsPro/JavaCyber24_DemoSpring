package be.tftic.java.spring.domain.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "\"user\"",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_name", columnNames = "user_name")
        }
)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String name;

    @Column(name = "user_password", nullable = false)
    private String password;

    @ColumnDefault("true")
    @Column(name = "user_active", nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "renter")
    private Set<Rental> rentals;

}
