package fr.eni.demo.bo.pk2;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class EtudiantPK2 implements Serializable {
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Column(name = "STUDENT_REGISTRATION", nullable = false, length = 255)
    private String immatriculation;
}
