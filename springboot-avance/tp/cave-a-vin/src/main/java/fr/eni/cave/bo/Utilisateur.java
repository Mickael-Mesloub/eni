package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(of = {"pseudo"})
@ToString(exclude = {"password"})
@Entity
@Table(name = "CAV_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @Column(name = "LOGIN", length = 255, nullable = false, unique = true)
    private String pseudo;

    @Column(name = "PASSWORD", length = 68, nullable = false, unique = true)
    private String password;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 150, nullable = false)
    private String prenom;
}
