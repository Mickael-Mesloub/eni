package fr.eni.cave.bo.vin;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"couleur", "region"})
@Data
@Builder
@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 250, message = "{bottle.name.size-error}")
    @Column(name = "NAME", length = 250, nullable = false, unique = true)
    private String nom;

    @Column(name = "SPARKLING")
    private Boolean petillant;

    @Size(min = 4, max = 4, message = "{bottle.vintage.size-error}")
    @Column(name = "VINTAGE")
    private String millesime;

    @Min(value = 1, message = "{bottle.quantity.min-error}")
    @Column(name = "QUANTITY")
    private Integer quantite;

    @Min(value = 1, message = "{bottle.price.min-error}")
    @Column(name = "PRICE", precision = 2)
    private Float prix;

    @NotNull(message = "{bottle.color.not-null}")
    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;

    @NotNull(message = "{bottle.region.not-null}")
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;
}
