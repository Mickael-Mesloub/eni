package fr.eni.cave.bo.proprio;

import fr.eni.cave.bo.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "CAV_OWNER")
public class Proprio extends Utilisateur {
    @Column(name = "CLIENT_NUMBER", length = 14, nullable = false)
    private String siret;
}
