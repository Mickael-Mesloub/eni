package fr.eni.cave.bo.vin;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"couleur", "region"})
@Data
@Builder
@Entity
@Table(name = "BOTTLE")
public class Bouteille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 250, nullable = false, unique = true)
    private String nom;

    @Column(name = "SPARKLING")
    private Boolean petillant;

    @Column(name = "VINTAGE")
    private String millesime;

    @Column(name = "QUANTITY")
    private Integer quantite;

    @Column(name = "PRICE", precision = 2)
    private Float prix;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;


}
