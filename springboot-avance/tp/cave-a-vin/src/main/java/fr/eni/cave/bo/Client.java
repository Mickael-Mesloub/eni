package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"pseudo"})
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "LOGIN", length = 255, nullable = false, unique = true)
    private String pseudo;

    @ToString.Exclude
    @Column(name = "PASSWORD", length = 68, nullable = false, unique = true)
    private String password;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 150, nullable = false)
    private String prenom;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;
}
