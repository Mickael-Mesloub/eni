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
    private BouteilleId bouteilleId;

    @Field(name = "name")
    private String nom;

}
