package fr.eni.demo.bo.stagiaire;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"etudiantENI"})
@Entity
@Table(name = "STUDENT_DATA")
public class DonneesPerso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_DATA_ID")
    private Integer id;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 90, nullable = false)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @OneToOne(mappedBy = "donneesPerso")
    private EtudiantENI etudiantENI;
}
