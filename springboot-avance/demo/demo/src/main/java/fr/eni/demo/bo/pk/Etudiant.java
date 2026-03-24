package fr.eni.demo.bo.pk;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "STUDENT")
@IdClass(EtudiantPK.class)
public class Etudiant {
    @Id
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Id
    @Column(name = "STUDENT_REGISTRATION", nullable = false, length = 255)
    private String immatriculation;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 90, nullable = false)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @Column(name = "CELL_PHONE_NUMBER", length = 12)
    private String numPort;
}
