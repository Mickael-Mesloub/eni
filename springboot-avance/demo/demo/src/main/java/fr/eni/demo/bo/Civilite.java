package fr.eni.demo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "CIVILITY")
public class Civilite {
    @Id
    private String cle;

    @Column(name = "LABEL", length = 20, nullable = false)
    private String libelle;
}
