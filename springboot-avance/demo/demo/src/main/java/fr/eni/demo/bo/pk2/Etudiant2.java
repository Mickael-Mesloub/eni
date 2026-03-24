package fr.eni.demo.bo.pk2;

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
@Table(name = "STUDENT2")
public class Etudiant2 {

    @EmbeddedId
    private EtudiantPK2 pk;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 90, nullable = false)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", length = 12)
    private String numDom;

    @Column(name = "CELL_PHONE_NUMBER", length = 12)
    private String numPort;
}
