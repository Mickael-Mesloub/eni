package fr.eni.cave.bo;

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
@Table(name = "ADDRESS")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;

    @Column(name = "STREET", length = 250, nullable = false)
    private String rue;

    @Column(name = "POSTAL_CODE", length = 5, nullable = false)
    private String codePostal;

    @Column(name = "CITY", length = 150, nullable = false)
    private String ville;
}
