package fr.eni.demo.bo;

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

@Document(collection = "reviews")
public class Avis {
    @Id
    private String id;

    @Field(name = "pedagogical_note")
    private Integer notePedagogique;

    @Field(name = "pedagogical_commentary")
    private String commentairePedagogique;

    @Field(name = "course_note")
    private Integer noteCours;

    @Field(name = "course_commentary")
    private String commentaireCours;

    @Field(name = "student")
    private Stagiaire stagiaire;
}
