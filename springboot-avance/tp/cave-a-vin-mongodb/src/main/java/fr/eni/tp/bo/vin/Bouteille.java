package fr.eni.tp.bo.vin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Document(collection = "bottles")
public class Bouteille {
    @Id
    @Field(name = "bottle_id")
    private Integer bouteilleId;

    @Field(name = "region_id")
    private Integer regionId;

    @Field(name = "color_id")
    private Integer couleurId;

    @Field(name = "name")
    private String nom;

}
