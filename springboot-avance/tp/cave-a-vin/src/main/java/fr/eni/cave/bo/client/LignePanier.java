package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@Builder
@Entity
@Table(name = "LINE")
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Integer id;

    @Column(name = "QUANTITY")
    private Integer quantite;

    @Column(name = "PRICE", precision = 2)
    private Float prix;
}
