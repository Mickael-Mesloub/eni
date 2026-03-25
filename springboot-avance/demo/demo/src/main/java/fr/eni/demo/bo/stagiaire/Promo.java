package fr.eni.demo.bo.stagiaire;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"etudiants"})
@EqualsAndHashCode(exclude = {"etudiants"})
@Builder
@Entity
@Table(name = "STUDENT_CLASS")
public class Promo {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 20, nullable = false)
    private String nom;

    // Fetch en LAZY pour éviter la jointure
    // => on ne récupère pas les étudiants, sinon, la requête risque d'être gourmande
    @OneToMany(mappedBy = "promo", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    // @Builder.Default permet d'initialiser la liste à vide, plutôt qu'à null
    private @Builder.Default List<EtudiantENI> etudiants = new ArrayList<>();

    @PreRemove
    public void preRemove() {
        etudiants.forEach(this::detachPromoFromStudent);
    }

    // Détacher la promo de chaque étudiant quand la promo est supprimée
    public void detachPromoFromStudent(EtudiantENI etudiantENI) {
        etudiantENI.setPromo(null);
    }
}
