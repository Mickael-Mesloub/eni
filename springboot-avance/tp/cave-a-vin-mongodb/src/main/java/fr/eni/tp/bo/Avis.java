package fr.eni.tp.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Document(collection = "reviews")
public class Avis {
    @Id
    private String id;

    @Field(name = "note")
    private Integer note;

    @Field(name = "commentary")
    private String commentaire;

    @Field(name = "date")
    private LocalDateTime date;

    @Field
    private Client client;
}
