package fr.eni.demo.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"}) // Comparer les employés QUE via leur immatriculation
@SuperBuilder
@Entity
@Table(name = "EMPLOYEE") // Nom de la table en base pour le mapping
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @NotBlank
    @Size(max = 90)
    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @NotBlank
    @Size(max = 90)
    @Column(name = "FIRST_NAME", length = 90, nullable = false)
    private String prenom;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "EMAIL", length = 255, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 90)
    @Column(name = "EMPLOYEE_REGISTRATION", length = 90, nullable = false, unique = true)
    private String immatriculation;

    @Size(max = 12)
    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @Size(max = 12)
    @Column(name = "CELL_PHONE_NUMBER", length = 12)
    private String numPort;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "CIVILITY_ID")
    private Civilite civilite;

}

