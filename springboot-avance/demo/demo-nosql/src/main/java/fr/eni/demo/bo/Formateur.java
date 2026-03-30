package fr.eni.demo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Document(collection = "trainers")
public class Formateur {
    @Id
    private String email;

    @Indexed(unique = true)
    @Field(name = "last_name")
    private String nom;

    @Indexed(unique = true)
    @Field(name = "first_name")
    private String prenom;
}
