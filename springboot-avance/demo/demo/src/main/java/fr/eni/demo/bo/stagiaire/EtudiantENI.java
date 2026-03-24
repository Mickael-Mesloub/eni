package fr.eni.demo.bo.stagiaire;

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
@Table(name = "ENI_STUDENT_DATA")
public class EtudiantENI {
    @Id
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Column(name = "STUDENT_REGISTRATION", nullable = false, length = 255)
    private String immatriculation;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DATA_ID")
    private DonneesPerso donneesPerso;
}
