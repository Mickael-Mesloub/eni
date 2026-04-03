package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@Builder
@Entity
@Table(name = "CAV_SHOPPING_CART")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID")
    private Integer id;

    @Column(name = "ORDER_NUMBER", length = 200)
    private String numCommande;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private Float prixTotal;

    @Column(name = "PAID")
    private Boolean paye;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private @Builder.Default List<LignePanier> lignesPanier = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

}
