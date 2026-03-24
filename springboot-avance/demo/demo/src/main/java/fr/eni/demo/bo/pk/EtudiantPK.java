package fr.eni.demo.bo.pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EtudiantPK implements Serializable {
    private String email;
    private String immatriculation;
}
