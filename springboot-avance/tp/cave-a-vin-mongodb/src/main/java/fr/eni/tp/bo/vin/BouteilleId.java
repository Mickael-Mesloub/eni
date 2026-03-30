package fr.eni.tp.bo.vin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BouteilleId implements Serializable {
    @Field(name = "bottle_id")
    private Integer idBouteille;

    @Field(name = "region_id")
    private Integer idRegion;

    @Field(name = "color_id")
    private Integer idCouleur;



}
