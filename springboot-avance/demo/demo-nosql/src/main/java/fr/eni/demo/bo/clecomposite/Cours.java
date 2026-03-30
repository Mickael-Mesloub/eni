package fr.eni.demo.bo.clecomposite;

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
@Document(collection = "computer_course")
public class Cours {
    @Id
    private CoursId coursId;

    @Field(name = "title")
    private String titre;

    @Field(name = "duration")
    private Integer duree;
}
